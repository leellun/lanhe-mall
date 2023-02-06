package com.newland.lanhe.product.entity;

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
 * 商品审核记录
 * </p>
 *
 * @author leellun
 * @since 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_vertify_record")
@ApiModel(value="ProductVertifyRecord对象", description="商品审核记录")
public class ProductVertifyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品审核记录id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核人")
    private String vertifyMan;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "反馈详情")
    private String detail;


}
