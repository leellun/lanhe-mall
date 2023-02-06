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
import java.time.LocalDateTime;

/**
 * <p>
 * 会员统计信息
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member_statistics_info")
@ApiModel(value="MemberStatisticsInfo对象", description="会员统计信息")
public class MemberStatisticsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员统计信息id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "累计消费金额")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "订单数量")
    private Integer orderCount;

    @ApiModelProperty(value = "优惠券数量")
    private Integer couponCount;

    @ApiModelProperty(value = "评价数")
    private Integer commentCount;

    @ApiModelProperty(value = "退货数量")
    private Integer returnOrderCount;

    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    @ApiModelProperty(value = "关注数量")
    private Integer attendCount;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansCount;

    @ApiModelProperty(value = "商品收藏数")
    private Integer collectProductCount;

    @ApiModelProperty(value = "专题收藏数")
    private Integer collectSubjectCount;

    @ApiModelProperty(value = "主题收藏数")
    private Integer collectTopicCount;

    @ApiModelProperty(value = "评论收藏数")
    private Integer collectCommentCount;

    @ApiModelProperty(value = "好友邀请数")
    private Integer inviteFriendCount;

    @ApiModelProperty(value = "最后一次下订单时间")
    private LocalDateTime recentOrderTime;


}
