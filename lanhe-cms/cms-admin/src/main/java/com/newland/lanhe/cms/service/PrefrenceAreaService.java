package com.newland.lanhe.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.cms.entity.PrefrenceArea;
import com.newland.lanhe.cms.model.dto.PrefrenceAreaProductRelationDto;

import java.util.List;

/**
 * <p>
 * 优选专区 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-02
 */
public interface PrefrenceAreaService extends IService<PrefrenceArea> {
    /**
     * 获取所有优选专区
     */
    List<PrefrenceArea> listAll();

    /**
     * 批量 插入 优选专区
     * @param productRelationInput
     * @param productId
     */
    void relateAndInsertList(List<PrefrenceAreaProductRelationDto> productRelationInput, Long productId);

    /**
     * 批量 更新 优选专区
     * @param productRelationInput
     * @param productId
     */
    void relateAndUpdateList(List<PrefrenceAreaProductRelationDto> productRelationInput, Long productId);

    /**
     * 通过id 查询  优选专区
     * @param productId
     * @return
     */
    List<PrefrenceAreaProductRelationDto> relationByProductId(Long productId);
}
