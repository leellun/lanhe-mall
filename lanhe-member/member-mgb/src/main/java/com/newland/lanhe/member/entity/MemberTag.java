package com.newland.lanhe.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户标签表
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_tag")
@ApiModel(value="MemberTag对象", description="用户标签表")
public class MemberTag implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "自动打标签完成订单数量")
    private Integer finishOrderCount;

    @ApiModelProperty(value = "自动打标签完成订单金额")
    private BigDecimal finishOrderAmount;


}
