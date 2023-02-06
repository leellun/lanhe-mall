package com.newland.lanhe.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_operate_log")
@ApiModel(value="ProductOperateLog对象", description="操作日志")
public class ProductOperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "旧价格")
    private BigDecimal priceOld;

    @ApiModelProperty(value = "新价格")
    private BigDecimal priceNew;

    @ApiModelProperty(value = "销售 就价格")
    private BigDecimal salePriceOld;

    @ApiModelProperty(value = "销售新价格")
    private BigDecimal salePriceNew;

    @ApiModelProperty(value = "赠送的积分")
    private Integer giftPointOld;

    @ApiModelProperty(value = "新赠送的积分")
    private Integer giftPointNew;

    @ApiModelProperty(value = "使用点限制旧")
    private Integer usePointLimitOld;

    @ApiModelProperty(value = "使用点限制新")
    private Integer usePointLimitNew;

    @ApiModelProperty(value = "操作人")
    private String operateMan;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
