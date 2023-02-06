package com.newland.lanhe.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.order.entity.OrderSetting;

/**
 * <p>
 * 订单设置表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface OrderSettingService extends IService<OrderSetting> {
    /**
     * 获取指定订单设置
     */
    OrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     */
    void update(Long id, OrderSetting orderSetting);
}
