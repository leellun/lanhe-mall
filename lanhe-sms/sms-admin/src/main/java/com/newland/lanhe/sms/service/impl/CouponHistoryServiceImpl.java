package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.CouponHistory;
import com.newland.lanhe.sms.mapper.CouponHistoryMapper;
import com.newland.lanhe.sms.service.CouponHistoryService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistory> implements CouponHistoryService {
    @Override
    public Page<CouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<CouponHistory> queryWrapper = Wrappers.lambdaQuery();
        if (couponId != null) {
            queryWrapper.eq(CouponHistory::getCouponId, couponId);
        }
        if (useStatus != null) {
            queryWrapper.eq(CouponHistory::getUseStatus, useStatus);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            queryWrapper.eq(CouponHistory::getOrderSn, orderSn);
        }
        return baseMapper.selectPage(PageWrapper.wrapper(PageEntity.page(pageNum, pageSize)), queryWrapper);
    }
}
