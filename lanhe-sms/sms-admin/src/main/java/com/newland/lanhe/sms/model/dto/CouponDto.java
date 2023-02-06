package com.newland.lanhe.sms.model.dto;

import com.newland.lanhe.sms.entity.Coupon;
import com.newland.lanhe.sms.entity.CouponProductCategoryRelation;
import com.newland.lanhe.sms.entity.CouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券信息封装，包括绑定商品和绑定分类
 */
@Data
public class CouponDto extends Coupon {
    @ApiModelProperty("优惠券绑定的商品")
    private List<CouponProductRelation> productRelationList;
    @ApiModelProperty("优惠券绑定的商品分类")
    private List<CouponProductCategoryRelation> productCategoryRelationList;

}
