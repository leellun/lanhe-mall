package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.Coupon;
import com.newland.lanhe.sms.model.dto.CouponDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface CouponService extends IService<Coupon> {
    /**
     * 添加优惠券
     */
    void create(CouponDto couponParam);

    /**
     * 根据优惠券id删除优惠券
     */
    void delete(Long id);

    /**
     * 根据优惠券id更新优惠券信息
     */
    void update(Long id, CouponDto couponParam);

    /**
     * 分页获取优惠券列表
     */
    Page<Coupon> list(String name, Integer type, Integer pageSize, Integer pageNo);

    /**
     * 获取优惠券详情
     * @param id 优惠券表id
     */
    CouponDto getItem(Long id);
}
