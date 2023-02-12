package com.newland.lanhe.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.cms.agent.PrefrenceAreaProductRelationAgent;
import com.newland.lanhe.cms.agent.SubjectProductRelationAgent;
import com.newland.lanhe.cms.model.dto.PrefrenceAreaProductRelationDto;
import com.newland.lanhe.cms.model.dto.SubjectProductRelationDto;
import com.newland.lanhe.enumeration.BasicEnum;
import com.newland.lanhe.enumeration.CommonErrorEnum;
import com.newland.lanhe.enumeration.ResultCode;
import com.newland.lanhe.exception.BusinessException;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.*;
import com.newland.lanhe.product.mapper.ProductAttributeValueMapper;
import com.newland.lanhe.product.mapper.ProductMapper;
import com.newland.lanhe.product.mapper.SkuStockMapper;
import com.newland.lanhe.product.model.dto.ProductDto;
import com.newland.lanhe.product.model.dto.ProductQueryDto;
import com.newland.lanhe.product.model.vo.ProductDetailVo;
import com.newland.lanhe.product.service.*;
import com.newland.lanhe.product.vo.ProductOutputVo;
import com.newland.lanhe.security.utils.SecurityUtil;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private SkuStockMapper skuStockMapper;
    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private SubjectProductRelationAgent cmsSubjectProductRelationClient;

    @Autowired
    private PrefrenceAreaProductRelationAgent cmsPrefrenceAreaProductRelationClient;
    @Autowired
    private MemberPriceService memberPriceService;
    @Autowired
    private ProductLadderService productLadderService;
    @Autowired
    private ProductFullReductionService productFullReductionService;
    @Autowired
    private SkuStockService skuStockService;
    @Autowired
    private ProductAttributeValueService productAttributeValueService;
    @Autowired
    private ProductVertifyRecordService productVertifyRecordService;
    @Autowired
    private ProductAttrPicService productAttrPicService;

    @Override
    public Page<Product> list(ProductQueryDto productQueryDto) {
        Page<Product> wrapper = PageWrapper.wrapper(productQueryDto);
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.<Product>lambdaQuery().eq(Product::getDeleteStatus, BasicEnum.NO.getCode());

        if (productQueryDto.getPublishStatus() != null) {
            queryWrapper.eq(Product::getPublishStatus, productQueryDto.getPublishStatus());
        }
        if (productQueryDto.getVerifyStatus() != null) {
            queryWrapper.eq(Product::getVerifyStatus, productQueryDto.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryDto.getKeyword())) {
            queryWrapper.and(w->w.like(Product::getKeywords, productQueryDto.getKeyword()).or().like(Product::getName, productQueryDto.getKeyword()));
        }
        if (!StringUtils.isEmpty(productQueryDto.getProductSn())) {
            queryWrapper.like(Product::getProductSn, productQueryDto.getProductSn());
        }
        if (productQueryDto.getBrandId() != null) {
            queryWrapper.eq(Product::getBrandId, productQueryDto.getBrandId());
        }
        if (productQueryDto.getProductCategoryId() != null) {
            queryWrapper.eq(Product::getProductCategoryId, productQueryDto.getProductCategoryId());
        }
        queryWrapper.orderByDesc(Product::getId);
        return baseMapper.selectPage(wrapper, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = BusinessException.class)
    public void add(ProductDto productDto) {
        //创建商品
        Product product = productDto;
        if(productDto.getProductAttrPics().size()>0){
            product.setPic(productDto.getProductAttrPics().get(0).getPic());
        }
        product.setId(null);
        baseMapper.insert(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        productDto.getMemberPriceList().forEach(item -> item.setProductId(productId));
        memberPriceService.saveBatch(productDto.getMemberPriceList());
        //阶梯价格
        productDto.getProductLadderList().forEach(item -> item.setProductId(productId));
        productLadderService.saveBatch(productDto.getProductLadderList());
        //满减价格
        productDto.getProductFullReductionList().forEach(item -> item.setProductId(productId));
        productFullReductionService.saveBatch(productDto.getProductFullReductionList());
        //处理sku的编码
        handleSkuStockCode(productDto.getSkuStockList(), productId);
        //添加sku库存信息
        productDto.getSkuStockList().forEach(item -> item.setProductId(productId));
        skuStockService.saveBatch(productDto.getSkuStockList());
        //添加商品参数,添加自定义商品规格
        productDto.getProductAttributeValueList().forEach(item -> item.setProductId(productId));
        productAttributeValueService.saveBatch(productDto.getProductAttributeValueList());
        //属性图片
        productDto.getProductAttrPics().forEach(item -> item.setProductId(productId));
        productAttrPicService.saveBatch(productDto.getProductAttrPics());
        //关联专题
        cmsSubjectProductRelationClient.relateAndInsertList(productDto.getSubjectProductRelationList(), productId);
        //关联优选
        cmsPrefrenceAreaProductRelationClient.relateAndInsertList(productDto.getPrefrenceAreaProductRelationList(), productId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = BusinessException.class)
    public void update(Long id, ProductDto productDto) {
        //更新商品信息
        Product product = productDto;
        if(productDto.getProductAttrPics().size()>0){
            product.setPic(productDto.getProductAttrPics().get(0).getPic());
        }
        product.setId(id);
        baseMapper.updateById(product);
        //会员价格
        memberPriceService.remove(Wrappers.<MemberPrice>lambdaQuery().eq(MemberPrice::getProductId, id));
        productDto.getMemberPriceList().forEach(item -> item.setProductId(id));
        memberPriceService.saveBatch(productDto.getMemberPriceList());
        //阶梯价格
        productLadderService.remove(Wrappers.<ProductLadder>lambdaQuery().eq(ProductLadder::getProductId, id));
        productDto.getProductLadderList().forEach(item -> item.setProductId(id));
        productLadderService.saveBatch(productDto.getProductLadderList());
        //满减价格
        productFullReductionService.remove(Wrappers.<ProductFullReduction>lambdaQuery().eq(ProductFullReduction::getProductId, id));
        productDto.getProductFullReductionList().forEach(item -> item.setProductId(id));
        productFullReductionService.saveBatch(productDto.getProductFullReductionList());
        //修改sku库存信息
        handleUpdateSkuStockList(id, productDto);
        //修改商品参数,添加自定义商品规格
        productAttributeValueMapper.delete(Wrappers.<ProductAttributeValue>lambdaQuery().eq(ProductAttributeValue::getProductId, id));
        productDto.getProductAttributeValueList().forEach(item -> item.setProductId(id));
        productAttributeValueService.saveBatch(productDto.getProductAttributeValueList());
        //属性图片
        productAttrPicService.remove(Wrappers.<ProductAttrPic>lambdaQuery().eq(ProductAttrPic::getProductId, id));
        productDto.getProductAttrPics().forEach(item -> item.setProductId(id));
        productAttrPicService.saveBatch(productDto.getProductAttrPics());
        //关联专题
        cmsSubjectProductRelationClient.relateAndUpdateList(productDto.getSubjectProductRelationList(), id);
        //关联优选
        cmsPrefrenceAreaProductRelationClient.relateAndUpdateList(productDto.getPrefrenceAreaProductRelationList(), id);
    }

    @Override
    public void delete(List<Long> ids) {
        int count = baseMapper.update(null, Wrappers.<Product>lambdaUpdate().set(Product::getDeleteStatus, BasicEnum.YES.getCode()).in(Product::getId, ids));
        AssertUtil.isTrue(count > 0, CommonErrorEnum.DELETE_ERROR);
    }

    @Override
    public void updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        int count = baseMapper.update(null, Wrappers.<Product>lambdaUpdate().set(Product::getVerifyStatus, verifyStatus).in(Product::getId, ids));
        AssertUtil.isTrue(count > 0, CommonErrorEnum.OPERATION_FAIL);
        List<ProductVertifyRecord> list = new ArrayList<>();
        //修改完审核状态后插入审核记录
        for (Long id : ids) {
            ProductVertifyRecord record = new ProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(LocalDateTime.now());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan(SecurityUtil.getLoginUser().getUsername());
            list.add(record);
        }
        productVertifyRecordService.saveBatch(list);
    }

    @Override
    public ProductDetailVo getProductDetail(Long id) {
        Product product = baseMapper.selectById(id);
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);

        RestResponse<List<SubjectProductRelationDto>> listCommonResult = cmsSubjectProductRelationClient.relationByProductId(id);

        // 关联主题
        if (listCommonResult.getCode() == ResultCode.SUCCESS.getCode()) {
            productDetailVo.setSubjectProductRelationList(listCommonResult.getData());
        }
        // 关联优选
        RestResponse<List<PrefrenceAreaProductRelationDto>> commonResult = cmsPrefrenceAreaProductRelationClient.relationByProductId(id);
        if (commonResult.getCode() == ResultCode.SUCCESS.getCode()) {
            productDetailVo.setPrefrenceAreaProductRelationList(commonResult.getData());
        }
        //设置会员价格
        productDetailVo.setMemberPriceList(memberPriceService.list(Wrappers.<MemberPrice>lambdaQuery().eq(MemberPrice::getProductId, id)));
        //阶梯价格
        productDetailVo.setProductLadderList(productLadderService.list(Wrappers.<ProductLadder>lambdaQuery().eq(ProductLadder::getProductId, id)));
        //满减价格
        productDetailVo.setProductFullReductionList(productFullReductionService.list(Wrappers.<ProductFullReduction>lambdaQuery().eq(ProductFullReduction::getProductId, id)));
        //sku库存信息
        productDetailVo.setSkuStockList(skuStockMapper.selectList(Wrappers.<SkuStock>lambdaQuery().eq(SkuStock::getProductId, id)));
        //属性图片
        productDetailVo.setProductAttrPics(productAttrPicService.list(Wrappers.<ProductAttrPic>lambdaQuery().eq(ProductAttrPic::getProductId, id)));
        //商品参数,添加自定义商品规格
        productDetailVo.setProductAttributeValueList(productAttributeValueMapper.selectList(Wrappers.<ProductAttributeValue>lambdaQuery().eq(ProductAttributeValue::getProductId, id)));
        return productDetailVo;
    }

    @Override
    public List<Product> list(String keyword) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Product::getDeleteStatus, BasicEnum.NO.getCode());
        if (org.apache.commons.lang.StringUtils.isNotEmpty(keyword)) {
            queryWrapper.and(wrapper -> wrapper.like(Product::getName, keyword).or().like(Product::getProductSn, keyword));
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public ProductOutputVo getProductByProductId(Long productId) {
        ProductOutputVo productOutputVo = new ProductOutputVo();
        Product product = baseMapper.selectById(productId);
        BeanUtils.copyProperties(product, productOutputVo);
        return productOutputVo;
    }

    @Override
    public void updatePublishStatus(List<Long> ids, Integer publishStatus) {
        int count = baseMapper.update(null, Wrappers.<Product>lambdaUpdate().set(Product::getPublishStatus, publishStatus).in(Product::getId, ids));
        AssertUtil.isTrue(count > 0, CommonErrorEnum.OPERATION_FAIL);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        int count = baseMapper.update(null, Wrappers.<Product>lambdaUpdate().set(Product::getRecommendStatus, recommendStatus).in(Product::getId, ids));
        AssertUtil.isTrue(count > 0, CommonErrorEnum.OPERATION_FAIL);
    }

    @Override
    public void updateNewStatus(List<Long> ids, Integer newStatus) {
        int count = baseMapper.update(null, Wrappers.<Product>lambdaUpdate().set(Product::getNewStatus, newStatus).in(Product::getId, ids));
        AssertUtil.isTrue(count > 0, CommonErrorEnum.OPERATION_FAIL);
    }

    /**
     * 修改sku库存信息
     *
     * @param id
     * @param productDto
     */
    private void handleUpdateSkuStockList(Long id, ProductDto productDto) {
        //当前的sku信息
        List<SkuStock> currSkuList = productDto.getSkuStockList();

        //当前没有sku直接删除
        if (CollUtil.isEmpty(currSkuList)) {
            skuStockMapper.delete(Wrappers.<SkuStock>lambdaQuery().eq(SkuStock::getProductId, id));
            return;
        }
        //获取初始sku信息
        List<SkuStock> oriStuList = skuStockMapper.selectList(Wrappers.<SkuStock>lambdaQuery().eq(SkuStock::getProductId, id));
        //获取新增sku信息
        List<SkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<SkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(SkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<SkuStock> removeSkuList = oriStuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList, id);
        handleSkuStockCode(updateSkuList, id);
        //新增sku
        if (CollUtil.isNotEmpty(insertSkuList)) {
            insertSkuList.forEach(item -> item.setProductId(id));
            skuStockService.saveBatch(insertSkuList);
        }
        //删除sku
        if (CollUtil.isNotEmpty(removeSkuList)) {
            List<Long> removeSkuIds = removeSkuList.stream().map(SkuStock::getId).collect(Collectors.toList());
            skuStockMapper.deleteBatchIds(removeSkuIds);
        }
        //修改sku
        if (CollUtil.isNotEmpty(updateSkuList)) {
            for (SkuStock SkuStock : updateSkuList) {
                skuStockMapper.updateById(SkuStock);
            }
        }

    }

    /**
     * 处理sku
     *
     * @param skuStockList
     * @param productId
     */
    private void handleSkuStockCode(List<SkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) {
            return;
        }
        for (int i = 0; i < skuStockList.size(); i++) {
            SkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }
}
