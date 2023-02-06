package com.newland.lanhe.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.order.entity.OrderReturnApply;
import com.newland.lanhe.order.model.dto.ReturnApplyQueryDto;
import com.newland.lanhe.order.model.dto.UpdateStatusDto;
import com.newland.lanhe.order.model.vo.OrderReturnApplyResultVo;

import java.util.List;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface OrderReturnApplyService extends IService<OrderReturnApply> {
    /**
     * 分页查询申请
     */
    Page<OrderReturnApply> list(ReturnApplyQueryDto returnApplyQueryDto);

    /**
     * 批量删除申请
     */
    void delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    void updateStatus(Long id, UpdateStatusDto updateStatusDto);

    /**
     * 获取指定申请详情
     */
    OrderReturnApplyResultVo getItem(Long id);
}
