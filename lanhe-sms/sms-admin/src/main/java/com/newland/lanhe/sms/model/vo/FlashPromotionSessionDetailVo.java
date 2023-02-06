package com.newland.lanhe.sms.model.vo;

import com.newland.lanhe.sms.entity.FlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 包含商品数量的场次信息
 * Created by macro on 2018/11/19.
 */
@Data
public class FlashPromotionSessionDetailVo extends FlashPromotionSession {
    @ApiModelProperty("商品数量")
    private Long productCount;
}
