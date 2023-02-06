package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.FlashPromotionProductRelation;
import com.newland.lanhe.sms.model.vo.FlashPromotionProductVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface FlashPromotionProductRelationService extends IService<FlashPromotionProductRelation> {
    /**
     * 批量添加关联
     */
    void create(List<FlashPromotionProductRelation> relationList);

    /**
     * 修改关联相关信息
     */
    void update(Long id, FlashPromotionProductRelation relation);

    /**
     * 删除关联
     */
    void delete(Long id);

    /**
     * 获取关联详情
     */
    FlashPromotionProductRelation getItem(Long id);

    /**
     * 分页查询相关商品及促销信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    Page<FlashPromotionProductVo> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 根据活动和场次id获取商品关系数量
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    long getCount(Long flashPromotionId, Long flashPromotionSessionId);
}
