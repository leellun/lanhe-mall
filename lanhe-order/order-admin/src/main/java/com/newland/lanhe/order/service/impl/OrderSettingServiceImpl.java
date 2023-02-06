package com.newland.lanhe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.order.entity.OrderSetting;
import com.newland.lanhe.order.mapper.OrderSettingMapper;
import com.newland.lanhe.order.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements OrderSettingService {

    @Override
    public OrderSetting getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void update(Long id, OrderSetting orderSetting) {
        orderSetting.setId(id);
        baseMapper.updateById(orderSetting);
    }
}
