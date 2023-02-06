package com.newland.lanhe.order.model.vo;

import com.newland.lanhe.order.entity.CompanyAddress;
import com.newland.lanhe.order.entity.OrderReturnApply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 */
@Data
public class OrderReturnApplyResultVo extends OrderReturnApply {
    @ApiModelProperty(value = "公司收货地址")
    private CompanyAddress companyAddress;
}
