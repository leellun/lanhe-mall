package com.newland.lanhe.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.exception.BusinessException;
import com.newland.lanhe.order.entity.CompanyAddress;
import com.newland.lanhe.order.entity.OrderReturnApply;
import com.newland.lanhe.order.mapper.CompanyAddressMapper;
import com.newland.lanhe.order.mapper.OrderReturnApplyMapper;
import com.newland.lanhe.order.model.dto.ReturnApplyQueryDto;
import com.newland.lanhe.order.model.dto.UpdateStatusDto;
import com.newland.lanhe.order.model.vo.OrderReturnApplyResultVo;
import com.newland.lanhe.order.service.OrderReturnApplyService;
import com.newland.mybatis.page.PageWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApply> implements OrderReturnApplyService {
    @Autowired
    private CompanyAddressMapper companyAddressMapper;

    @Override
    public Page<OrderReturnApply> list(ReturnApplyQueryDto queryDto) {
        LambdaQueryWrapper<OrderReturnApply> queryWrapper = Wrappers.lambdaQuery();
        if (queryDto.getId() != null) {
            queryWrapper.eq(OrderReturnApply::getId, queryDto.getId());
        }
        if (queryDto.getStatus() != null) {
            queryWrapper.eq(OrderReturnApply::getStatus, queryDto.getStatus());
        }
        if (StringUtils.isNotEmpty(queryDto.getHandleMan())) {
            queryWrapper.eq(OrderReturnApply::getHandleMan, queryDto.getHandleMan());
        }
        if (StringUtils.isNotEmpty(queryDto.getCreateTime())) {
            queryWrapper.likeRight(OrderReturnApply::getCreateTime, queryDto.getCreateTime());
        }
        if (StringUtils.isNotEmpty(queryDto.getHandleTime())) {
            queryWrapper.likeRight(OrderReturnApply::getHandleTime, queryDto.getHandleTime());
        }
        if (StringUtils.isNotEmpty(queryDto.getReceiverKeyword())) {
            queryWrapper.and(wrapper -> wrapper.like(OrderReturnApply::getReturnName, queryDto.getReceiverKeyword()).or().like(OrderReturnApply::getReturnPhone, queryDto.getReceiverKeyword()));
        }
        return baseMapper.selectPage(PageWrapper.wrapper(queryDto), queryWrapper);
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.delete(Wrappers.<OrderReturnApply>lambdaQuery().eq(OrderReturnApply::getStatus, 3).in(OrderReturnApply::getId, ids));
    }

    @Override
    public void updateStatus(Long id, UpdateStatusDto updateStatusDto) {
        Integer status = updateStatusDto.getStatus();
        OrderReturnApply returnApply = new OrderReturnApply();
        if (status.equals(1)) {
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(updateStatusDto.getReturnAmount());
            returnApply.setCompanyAddressId(updateStatusDto.getCompanyAddressId());
            returnApply.setHandleTime(LocalDateTime.now());
            returnApply.setHandleMan(updateStatusDto.getHandleMan());
            returnApply.setHandleNote(updateStatusDto.getHandleNote());
        } else if (status.equals(2)) {
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(LocalDateTime.now());
            returnApply.setReceiveMan(updateStatusDto.getReceiveMan());
            returnApply.setReceiveNote(updateStatusDto.getReceiveNote());
        } else if (status.equals(3)) {
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(LocalDateTime.now());
            returnApply.setHandleMan(updateStatusDto.getHandleMan());
            returnApply.setHandleNote(updateStatusDto.getHandleNote());
        } else {
            throw new BusinessException("操作失败");
        }
        baseMapper.updateById(returnApply);
    }

    @Override
    public OrderReturnApplyResultVo getItem(Long id) {
        OrderReturnApply orderReturnApply = baseMapper.selectById(id);
        OrderReturnApplyResultVo orderReturnApplyResultVo = new OrderReturnApplyResultVo();
        BeanUtils.copyProperties(orderReturnApply, orderReturnApplyResultVo);
        CompanyAddress companyAddress = companyAddressMapper.selectById(orderReturnApply.getCompanyAddressId());
        orderReturnApplyResultVo.setCompanyAddress(companyAddress);
        return orderReturnApplyResultVo;
    }
}
