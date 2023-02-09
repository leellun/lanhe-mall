package com.newland.lanhe.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.product.dao.ProductAttributeDao;
import com.newland.lanhe.product.entity.ProductAttribute;
import com.newland.lanhe.product.entity.ProductAttributeCategory;
import com.newland.lanhe.product.mapper.ProductAttributeCategoryMapper;
import com.newland.lanhe.product.mapper.ProductAttributeMapper;
import com.newland.lanhe.product.model.dto.ProductAttributeDto;
import com.newland.lanhe.product.model.vo.ProductAttrInfoVo;
import com.newland.lanhe.product.service.ProductAttributeService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {
    @Autowired
    private ProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Autowired
    private ProductAttributeDao productAttributeDao;

    @Override
    public Page<ProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNo) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setDesc(true);
        pageEntity.setOrder("sort");
        Page<ProductAttribute> wrapper = PageWrapper.wrapper(pageEntity);
        return baseMapper.selectPage(wrapper, Wrappers.<ProductAttribute>lambdaQuery().eq(ProductAttribute::getProductAttributeCategoryId, cid).eq(ProductAttribute::getType, type));
    }

    @Override
    public List<ProductAttribute> getList(Long cid, Integer type) {
        return baseMapper.selectList(Wrappers.<ProductAttribute>lambdaQuery().eq(ProductAttribute::getProductAttributeCategoryId, cid).eq(ProductAttribute::getType, type).orderByDesc(ProductAttribute::getSort));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductAttributeDto productAttributeDto) {
        ProductAttribute productAttribute = new ProductAttribute();
        BeanUtils.copyProperties(productAttributeDto, productAttribute);
        baseMapper.insert(productAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        ProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectById(productAttribute.getProductAttributeCategoryId());
        if (productAttribute.getType() == 0) {
            productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() + 1);
        } else if (productAttribute.getType() == 1) {
            productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() + 1);
        }
        productAttributeCategoryMapper.updateById(productAttributeCategory);
    }

    @Override
    public void update(Long id, ProductAttributeDto productAttributeParam) {
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        baseMapper.updateById(productAttribute);
    }

    @Override
    public ProductAttribute getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        ProductAttribute productAttribute = baseMapper.selectById(ids.get(0));
        Integer type = productAttribute.getType();
        ProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectById(productAttribute.getProductAttributeCategoryId());
        int count = baseMapper.deleteBatchIds(ids);
        //删除完成后修改数量
        if (type == 0) {
            if (productAttributeCategory.getAttributeCount() >= count) {
                productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() - count);
            } else {
                productAttributeCategory.setAttributeCount(0);
            }
        } else if (type == 1) {
            if (productAttributeCategory.getParamCount() >= count) {
                productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() - count);
            } else {
                productAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateById(productAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfoVo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }
}
