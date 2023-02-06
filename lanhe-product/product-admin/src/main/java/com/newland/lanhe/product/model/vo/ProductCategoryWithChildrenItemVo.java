package com.newland.lanhe.product.model.vo;

import com.newland.lanhe.product.entity.ProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品分類帶children
 */
@Data
public class ProductCategoryWithChildrenItemVo extends ProductCategory {
    @ApiModelProperty("子级分类")
    private List<ProductCategory> children;

}
