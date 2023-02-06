package com.newland.lanhe.sms.model.vo;

import com.newland.lanhe.product.vo.ProductOutputVo;
import com.newland.lanhe.sms.entity.FlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 限时购及商品信息封装
 */
@Data
public class FlashPromotionProductVo extends FlashPromotionProductRelation {

    @ApiModelProperty("关联商品")
    private ProductOutputVo product;
}
