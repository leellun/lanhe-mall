package com.newland.lanhe.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductOutputVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    @ApiModelProperty(value = "货号")
    private String productSn;
    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    private BigDecimal price;
    @ApiModelProperty(value = "库存")
    private Integer stock;

}
