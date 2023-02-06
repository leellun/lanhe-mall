package com.newland.lanhe.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.order.entity.Order;
import com.newland.lanhe.order.model.dto.MoneyInfoDto;
import com.newland.lanhe.order.model.dto.OrderDeliveryDto;
import com.newland.lanhe.order.model.dto.OrderQueryDto;
import com.newland.lanhe.order.model.dto.ReceiverInfoDto;
import com.newland.lanhe.order.model.vo.OrderDetailVo;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface OrderService extends IService<Order> {
    /**
     * 订单查询
     */
    Page<Order> list(OrderQueryDto orderQueryDto);

    /**
     * 批量发货
     */
    int delivery(List<OrderDeliveryDto> orderDeliveryDtos);

    /**
     * 批量关闭订单
     */
    void close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    void delete(List<Long> ids);

    /**
     * 获取指定订单详情
     */
    OrderDetailVo detail(Long id);

    /**
     * 修改订单收货人信息
     */
    void updateReceiverInfo(ReceiverInfoDto receiverInfoDto);

    /**
     * 修改订单费用信息
     */
    void updateMoneyInfo(MoneyInfoDto moneyInfoDto);

    /**
     * 修改订单备注
     */
    void updateNote(Long id, String note, Integer status);
}
