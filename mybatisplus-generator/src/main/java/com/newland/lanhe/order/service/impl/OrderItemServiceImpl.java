package com.newland.lanhe.order.service.impl;

import com.newland.lanhe.order.entity.OrderItem;
import com.newland.lanhe.order.mapper.OrderItemMapper;
import com.newland.lanhe.order.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
