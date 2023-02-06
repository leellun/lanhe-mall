package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.FlashPromotion;

import java.util.List;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface FlashPromotionService extends IService<FlashPromotion> {
    /**
     * 添加活动
     */
    void create(FlashPromotion flashPromotion);

    /**
     * 修改指定活动
     */
    void update(Long id, FlashPromotion flashPromotion);

    /**
     * 删除单个活动
     */
    void delete(Long id);

    /**
     * 修改上下线状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 获取详细信息
     */
    FlashPromotion getItem(Long id);

    /**
     * 分页查询活动
     */
    Page<FlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);
}
