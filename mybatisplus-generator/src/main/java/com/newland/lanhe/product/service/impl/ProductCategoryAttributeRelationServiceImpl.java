package com.newland.lanhe.product.service.impl;

import com.newland.lanhe.product.entity.ProductCategoryAttributeRelation;
import com.newland.lanhe.product.mapper.ProductCategoryAttributeRelationMapper;
import com.newland.lanhe.product.service.ProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements ProductCategoryAttributeRelationService {

}
