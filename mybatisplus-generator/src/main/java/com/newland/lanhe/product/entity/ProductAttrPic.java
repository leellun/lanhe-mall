package com.newland.lanhe.product.entity;

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
 * 属性图片表
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_attr_pic")
@ApiModel(value="ProductAttrPic对象", description="属性图片表")
public class ProductAttrPic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性图片关联id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品属性id")
    private Long productAttributeId;

    @ApiModelProperty(value = "图片")
    private String pic;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
