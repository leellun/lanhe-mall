package com.newland.lanhe.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 限时购通知记录
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_flash_promotion_log")
@ApiModel(value="FlashPromotionLog对象", description="限时购通知记录")
public class FlashPromotionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "限时购通知记录id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员id")
    private Integer memberId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "会员手机号")
    private String memberPhone;

    @ApiModelProperty(value = "商品名称")
    private String productName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "会员订阅时间")
    private LocalDateTime subscribeTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "通知时间")
    private LocalDateTime sendTime;


}
