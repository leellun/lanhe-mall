package com.newland.lanhe.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.order.entity.OrderReturnReason;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface OrderReturnReasonService extends IService<OrderReturnReason> {
    /**
     * 添加订单原因
     */
    void create(OrderReturnReason returnReason);

    /**
     * 修改退货原因
     */
    void update(Long id, OrderReturnReason returnReason);

    /**
     * 批量删除退货原因
     */
    void delete(List<Long> ids);

    /**
     * 分页获取退货原因
     */
    Page<OrderReturnReason> list(Integer pageSize, Integer pageNo);

    /**
     * 批量修改退货原因状态
     */
    void updateStatus(List<Long> ids, Integer status);

    /**
     * 获取单个退货原因详情信息
     */
    OrderReturnReason getItem(Long id);
}
