package com.newland.lanhe.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.exception.BusinessException;
import com.newland.lanhe.order.entity.OrderReturnReason;
import com.newland.lanhe.order.mapper.OrderReturnReasonMapper;
import com.newland.lanhe.order.service.OrderReturnReasonService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReason> implements OrderReturnReasonService {
    @Autowired
    private OrderReturnReasonMapper returnReasonMapper;
    @Override
    public void create(OrderReturnReason returnReason) {
        returnReason.setCreateTime(LocalDateTime.now());
        returnReasonMapper.insert(returnReason);
    }

    @Override
    public void update(Long id, OrderReturnReason returnReason) {
        returnReason.setId(id);
        returnReasonMapper.updateById(returnReason);
    }

    @Override
    public void delete(List<Long> ids) {
        returnReasonMapper.deleteBatchIds(ids);
    }

    @Override
    public Page<OrderReturnReason> list(Integer pageSize, Integer pageNum) {
        PageEntity pageEntity=PageEntity.page(pageNum,pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return baseMapper.selectPage(PageWrapper.wrapper(pageEntity), Wrappers.emptyWrapper());
    }

    @Override
    public void updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            throw new BusinessException("操作失败");
        }
        OrderReturnReason record = new OrderReturnReason();
        record.setStatus(status);
        returnReasonMapper.update(record, Wrappers.<OrderReturnReason>lambdaUpdate().in(OrderReturnReason::getId,ids));
    }

    @Override
    public OrderReturnReason getItem(Long id) {
        return returnReasonMapper.selectById(id);
    }
}
