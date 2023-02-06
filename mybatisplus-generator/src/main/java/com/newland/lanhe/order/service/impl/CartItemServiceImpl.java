package com.newland.lanhe.order.service.impl;

import com.newland.lanhe.order.entity.CartItem;
import com.newland.lanhe.order.mapper.CartItemMapper;
import com.newland.lanhe.order.service.CartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}
