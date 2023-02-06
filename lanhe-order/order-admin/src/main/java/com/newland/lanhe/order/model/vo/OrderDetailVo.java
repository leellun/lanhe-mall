package com.newland.lanhe.order.model.vo;

import com.newland.lanhe.order.entity.Order;
import com.newland.lanhe.order.entity.OrderItem;
import com.newland.lanhe.order.entity.OrderOperateHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 订单详情信息
 */
@Data
public class OrderDetailVo extends Order {
    @ApiModelProperty("订单商品列表")
    private List<OrderItem> orderItemList;
    @ApiModelProperty("订单操作记录列表")
    private List<OrderOperateHistory> historyList;
}
