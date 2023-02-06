package com.newland.lanhe.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.order.dao.OrderDao;
import com.newland.lanhe.order.entity.Order;
import com.newland.lanhe.order.entity.OrderItem;
import com.newland.lanhe.order.entity.OrderOperateHistory;
import com.newland.lanhe.order.mapper.OrderItemMapper;
import com.newland.lanhe.order.mapper.OrderMapper;
import com.newland.lanhe.order.mapper.OrderOperateHistoryMapper;
import com.newland.lanhe.order.model.dto.MoneyInfoDto;
import com.newland.lanhe.order.model.dto.OrderDeliveryDto;
import com.newland.lanhe.order.model.dto.OrderQueryDto;
import com.newland.lanhe.order.model.dto.ReceiverInfoDto;
import com.newland.lanhe.order.model.vo.OrderDetailVo;
import com.newland.lanhe.order.service.OrderOperateHistoryService;
import com.newland.lanhe.order.service.OrderService;
import com.newland.lanhe.security.utils.SecurityUtil;
import com.newland.mybatis.page.PageWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderOperateHistoryService orderOperateHistoryService;
    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;

    @Override
    public Page<Order> list(OrderQueryDto queryDto) {
        LambdaQueryWrapper<Order> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Order::getDeleteStatus, 0);
        if (StringUtils.isNotEmpty(queryDto.getOrderSn())) {
            queryWrapper.eq(Order::getOrderSn, queryDto.getOrderSn());
        }
        if (queryDto.getStatus() != null) {
            queryWrapper.eq(Order::getStatus, queryDto.getStatus());
        }
        if (queryDto.getSourceType() != null) {
            queryWrapper.eq(Order::getSourceType, queryDto.getSourceType());
        }
        if (queryDto.getOrderType() != null) {
            queryWrapper.eq(Order::getOrderType, queryDto.getOrderType());
        }
        if (StringUtils.isNotEmpty(queryDto.getCreateTime())) {
            queryWrapper.likeRight(Order::getCreateTime, queryDto.getCreateTime());
        }
        if (StringUtils.isNotEmpty(queryDto.getReceiverKeyword())) {
            queryWrapper.and(wrapper -> wrapper.like(Order::getReceiverName, queryDto.getReceiverKeyword()).or().like(Order::getReceiverPhone, queryDto.getReceiverKeyword()));
        }
        return baseMapper.selectPage(PageWrapper.wrapper(queryDto), queryWrapper);
    }

    @Override
    public int delivery(List<OrderDeliveryDto> deliveryParamList) {
        //批量发货
        int count = orderDao.delivery(deliveryParamList);
        //添加操作记录
        List<OrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(OrderDeliveryParam -> {
                    OrderOperateHistory history = new OrderOperateHistory();
                    history.setOrderId(OrderDeliveryParam.getOrderId());
                    history.setCreateTime(LocalDateTime.now());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryService.saveBatch(operateHistoryList);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void close(List<Long> ids, String note) {
        Order record = new Order();
        record.setStatus(4);
        baseMapper.update(record, Wrappers.<Order>lambdaUpdate().eq(Order::getDeleteStatus, 0).in(Order::getId, ids));
        List<OrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OrderOperateHistory history = new OrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(LocalDateTime.now());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:" + note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryService.saveBatch(historyList);
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.update(null, Wrappers.<Order>lambdaUpdate().set(Order::getDeleteStatus, 1).eq(Order::getDeleteStatus, 0).in(Order::getId, ids));
    }

    @Override
    public OrderDetailVo detail(Long id) {
        Order order = baseMapper.selectById(id);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        BeanUtils.copyProperties(order, orderDetailVo);
        List<OrderItem> orderItems = orderItemMapper.selectList(Wrappers.<OrderItem>lambdaQuery().eq(OrderItem::getOrderId, id));
        List<OrderOperateHistory> operateHistories = orderOperateHistoryMapper.selectList(Wrappers.<OrderOperateHistory>lambdaQuery().eq(OrderOperateHistory::getOrderId, id));
        orderDetailVo.setOrderItemList(orderItems);
        orderDetailVo.setHistoryList(operateHistories);
        return orderDetailVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReceiverInfo(ReceiverInfoDto receiverInfoDto) {
        Order order = new Order();
        order.setId(receiverInfoDto.getOrderId());
        order.setReceiverName(receiverInfoDto.getReceiverName());
        order.setReceiverPhone(receiverInfoDto.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoDto.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoDto.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoDto.getReceiverProvince());
        order.setReceiverCity(receiverInfoDto.getReceiverCity());
        order.setReceiverRegion(receiverInfoDto.getReceiverRegion());
        order.setModifyTime(LocalDateTime.now());
        baseMapper.updateById(order);
        //插入操作记录
        OrderOperateHistory history = new OrderOperateHistory();
        history.setOrderId(receiverInfoDto.getOrderId());
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoDto.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMoneyInfo(MoneyInfoDto moneyInfoDto) {
        Order order = new Order();
        order.setId(moneyInfoDto.getOrderId());
        order.setFreightAmount(moneyInfoDto.getFreightAmount());
        order.setDiscountAmount(moneyInfoDto.getDiscountAmount());
        order.setModifyTime(LocalDateTime.now());
        baseMapper.updateById(order);
        //插入操作记录
        OrderOperateHistory history = new OrderOperateHistory();
        history.setOrderId(moneyInfoDto.getOrderId());
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan(SecurityUtil.getLoginUser().getUsername());
        history.setOrderStatus(moneyInfoDto.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
    }

    @Override
    public void updateNote(Long id, String note, Integer status) {
        Order order = new Order();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(LocalDateTime.now());
        int count = baseMapper.updateById(order);
        OrderOperateHistory history = new OrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan(SecurityUtil.getLoginUser().getUsername());
        history.setOrderStatus(status);
        history.setNote("修改备注信息：" + note);
        orderOperateHistoryMapper.insert(history);
    }
}
