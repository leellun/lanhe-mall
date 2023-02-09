package com.newland.lanhe.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.cms.entity.Subject;
import com.newland.lanhe.cms.model.dto.SubjectProductRelationDto;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-02
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 查询所有专题
     */
    List<Subject> listAll();

    /**
     * 分页查询专题
     */
    Page<Subject> list(String keyword, Integer pageNo, Integer pageSize);

    /**
     * 批量插入专题
     * @param productRelationInputs
     * @param productId
     */
    void relateAndInsertList(List<SubjectProductRelationDto> productRelationInputs, Long productId);

    /**
     * 批量更新
     * @param productRelationInputs
     * @param productId
     */
    void relateAndUpdateList(List<SubjectProductRelationDto> productRelationInputs, Long productId);

    /**
     * 通过id查询关联优选
     * @param productId
     * @return
     */
    List<SubjectProductRelationDto> relationByProductId(Long productId);
}
