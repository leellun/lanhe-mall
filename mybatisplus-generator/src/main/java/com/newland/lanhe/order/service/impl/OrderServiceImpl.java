package com.newland.lanhe.order.service.impl;

import com.newland.lanhe.order.entity.Order;
import com.newland.lanhe.order.mapper.OrderMapper;
import com.newland.lanhe.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
