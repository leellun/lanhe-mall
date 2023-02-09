package com.newland.lanhe.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.product.dao.ProductAttributeCategoryDao;
import com.newland.lanhe.product.entity.ProductAttributeCategory;
import com.newland.lanhe.product.mapper.ProductAttributeCategoryMapper;
import com.newland.lanhe.product.model.vo.ProductAttributeCategoryItemVo;
import com.newland.lanhe.product.service.ProductAttributeCategoryService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {
    @Autowired
    private ProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Autowired
    private ProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public void create(String name) {
        ProductAttributeCategory productAttributeCategory = new ProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategoryMapper.insert(productAttributeCategory);
    }

    @Override
    public void update(Long id, String name) {
        ProductAttributeCategory productAttributeCategory = new ProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        productAttributeCategoryMapper.updateById(productAttributeCategory);
    }

    @Override
    public void delete(Long id) {
        productAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public ProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectById(id);
    }

    @Override
    public Page<ProductAttributeCategory> getList(Integer pageSize, Integer pageNo) {
        return productAttributeCategoryMapper.selectPage(PageWrapper.wrapper(PageEntity.page(pageNo, pageSize)), Wrappers.lambdaQuery());
    }

    @Override
    public List<ProductAttributeCategoryItemVo> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }
}
