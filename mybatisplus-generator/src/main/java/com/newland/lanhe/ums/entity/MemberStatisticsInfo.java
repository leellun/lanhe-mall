package com.newland.lanhe.ums.entity;

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
 * 会员统计信息
 * </p>
 *
 * @author leellun
 * @since 2023-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_statistics_info")
@ApiModel(value="MemberStatisticsInfo对象", description="会员统计信息")
public class MemberStatisticsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "累计消费金额")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "累计优惠金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "订单数量")
    private Integer orderCount;

    @ApiModelProperty(value = "退货数量")
    private Integer returnOrderCount;


}
