package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.FlashPromotionSession;
import com.newland.lanhe.sms.model.vo.FlashPromotionSessionDetailVo;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface FlashPromotionSessionService extends IService<FlashPromotionSession> {
    /**
     * 添加场次
     */
    void create(FlashPromotionSession promotionSession);

    /**
     * 修改场次
     */
    void update(Long id, FlashPromotionSession promotionSession);

    /**
     * 修改场次启用状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 删除场次
     */
    void delete(Long id);

    /**
     * 获取详情
     */
    FlashPromotionSession getItem(Long id);

    /**
     * 根据启用状态获取场次列表
     */
    List<FlashPromotionSession> list();

    /**
     * 获取全部可选场次及其数量
     */
    List<FlashPromotionSessionDetailVo> selectList(Long flashPromotionId);
}
