package com.newland.lanhe.order.mapper;

import com.newland.lanhe.order.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单中所包含的商品 Mapper 接口
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Repository
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
