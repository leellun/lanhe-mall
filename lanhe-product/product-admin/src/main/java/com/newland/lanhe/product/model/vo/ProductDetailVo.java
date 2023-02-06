package com.newland.lanhe.product.model.vo;

import com.newland.lanhe.product.model.dto.ProductDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产品详情vo
 * Author: leell
 * Date: 2023/2/2 15:52:36
 */
@Data
public class ProductDetailVo extends ProductDto {
    @ApiModelProperty("商品所选分类的父id")
    private Long cateParentId;
}
