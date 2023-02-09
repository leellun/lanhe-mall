package com.newland.lanhe.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.enumeration.BasicEnum;
import com.newland.lanhe.product.dao.ProductCategoryDao;
import com.newland.lanhe.product.entity.Product;
import com.newland.lanhe.product.entity.ProductCategory;
import com.newland.lanhe.product.entity.ProductCategoryAttributeRelation;
import com.newland.lanhe.product.mapper.ProductCategoryAttributeRelationMapper;
import com.newland.lanhe.product.mapper.ProductCategoryMapper;
import com.newland.lanhe.product.mapper.ProductMapper;
import com.newland.lanhe.product.model.dto.ProductCategoryDto;
import com.newland.lanhe.product.model.vo.ProductCategoryWithChildrenItemVo;
import com.newland.lanhe.product.service.ProductCategoryAttributeRelationService;
import com.newland.lanhe.product.service.ProductCategoryService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryAttributeRelationService productCategoryAttributeRelationService;
    @Autowired
    private ProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(productCategoryDto, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        productCategoryMapper.insert(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = productCategoryDto.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     *
     * @param productCategoryId      商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<ProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            ProductCategoryAttributeRelation relation = new ProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationService.saveBatch(relationList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(productCategoryDto, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        Product product = new Product();
        product.setProductCategoryName(productCategory.getName());
        productMapper.update(product, Wrappers.<Product>lambdaUpdate().eq(Product::getProductCategoryId, id));
        //同时更新筛选属性的信息
        productCategoryAttributeRelationMapper.delete(Wrappers.<ProductCategoryAttributeRelation>lambdaQuery().eq(ProductCategoryAttributeRelation::getProductCategoryId, id));
        if (!CollectionUtils.isEmpty(productCategoryDto.getProductAttributeIdList())) {
            insertRelationList(id, productCategoryDto.getProductAttributeIdList());
        }
        productCategoryMapper.updateById(productCategory);
    }

    @Override
    public Page<ProductCategory> getList(Long parentId, Integer pageSize, Integer pageNo) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return productCategoryMapper.selectPage(PageWrapper.wrapper(pageEntity), Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getParentId, parentId));
    }

    @Override
    public void delete(Long id) {
        productCategoryMapper.deleteById(id);
    }

    @Override
    public ProductCategory getItem(Long id) {
        return productCategoryMapper.selectById(id);
    }

    @Override
    public void updateNavStatus(List<Long> ids, Integer navStatus) {
        productCategoryMapper.update(null, Wrappers.<ProductCategory>lambdaUpdate()
                .set(ProductCategory::getNavStatus, navStatus)
                .in(ProductCategory::getId, ids));
    }

    @Override
    public void updateShowStatus(List<Long> ids, Integer showStatus) {
        productCategoryMapper.update(null, Wrappers.<ProductCategory>lambdaUpdate()
                .set(ProductCategory::getShowStatus, showStatus)
                .in(ProductCategory::getId, ids));
    }

    @Override
    public List<ProductCategoryWithChildrenItemVo> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(ProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            ProductCategory parentCategory = productCategoryMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    @Override
    public List<ProductCategory> getSubCategorys(Long pid) {
        if (pid == null || pid == 0) {
            return baseMapper.selectList(Wrappers.<ProductCategory>lambdaQuery().isNull(ProductCategory::getParentId).orderByDesc(ProductCategory::getSort));
        } else {
            return baseMapper.selectList(Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getParentId, pid).orderByDesc(ProductCategory::getSort));
        }
    }
}
