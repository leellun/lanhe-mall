package com.newland.lanhe.product.model.vo;

import com.newland.lanhe.product.entity.ProductAttribute;
import com.newland.lanhe.product.entity.ProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 包含有分类下属性的dto
 */
@Data
public class ProductAttributeCategoryItemVo extends ProductAttributeCategory {
    @ApiModelProperty(value = "商品属性列表")
    private List<ProductAttribute> productAttributeList;
}
