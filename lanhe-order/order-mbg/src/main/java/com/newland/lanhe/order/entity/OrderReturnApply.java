package com.newland.lanhe.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单退货申请
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oms_order_return_apply")
@ApiModel(value="OrderReturnApply对象", description="订单退货申请")
public class OrderReturnApply implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "收货地址表id")
    private Long companyAddressId;

    @ApiModelProperty(value = "退货商品id")
    private Long productId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "申请时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "会员用户名")
    private String memberUsername;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty(value = "退货人姓名")
    private String returnName;

    @ApiModelProperty(value = "退货人电话")
    private String returnPhone;

    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "处理时间")
    private LocalDateTime handleTime;

    @ApiModelProperty(value = "商品图片")
    private String productPic;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品品牌")
    private String productBrand;

    @ApiModelProperty(value = "商品销售属性：颜色：红色；尺码：xl;")
    private String productAttr;

    @ApiModelProperty(value = "退货数量")
    private Integer productCount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品实际支付单价")
    private BigDecimal productRealPrice;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "凭证图片，以逗号隔开")
    private String proofPics;

    @ApiModelProperty(value = "处理备注")
    private String handleNote;

    @ApiModelProperty(value = "处理人员")
    private String handleMan;

    @ApiModelProperty(value = "收货人")
    private String receiveMan;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收货时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "收货备注")
    private String receiveNote;


}
