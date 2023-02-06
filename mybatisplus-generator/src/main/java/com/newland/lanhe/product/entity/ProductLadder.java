package com.newland.lanhe.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品)
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_ladder")
@ApiModel(value="ProductLadder对象", description="产品阶梯价格表(只针对同商品)")
public class ProductLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品阶梯价格id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "满足的商品数量")
    private Integer count;

    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "折后价格")
    private BigDecimal price;


}
