package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.HomeRecommendSubject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface HomeRecommendSubjectService extends IService<HomeRecommendSubject> {
    /**
     * 添加首页推荐
     */
    void create(List<HomeRecommendSubject> recommendSubjectList);

    /**
     * 修改推荐排序
     */
    void updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐
     */
    void delete(List<Long> ids);

    /**
     * 更新推荐状态
     */
    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     */
    Page<HomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
