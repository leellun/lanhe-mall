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
 * 产品满减表(只针对同商品)
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_full_reduction")
@ApiModel(value="ProductFullReduction对象", description="产品满减表(只针对同商品)")
public class ProductFullReduction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品满减表id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "满减金额")
    private BigDecimal fullPrice;

    @ApiModelProperty(value = "降低金额")
    private BigDecimal reducePrice;


}
