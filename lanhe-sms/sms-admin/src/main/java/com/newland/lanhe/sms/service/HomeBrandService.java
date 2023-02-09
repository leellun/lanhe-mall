package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.HomeBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface HomeBrandService extends IService<HomeBrand> {
    /**
     * 添加首页品牌推荐
     */
    void create(List<HomeBrand> homeBrandList);

    /**
     * 修改品牌推荐排序
     */
    void updateSort(Long id, Integer sort);

    /**
     * 批量删除品牌推荐
     */
    void delete(List<Long> ids);

    /**
     * 更新推荐状态
     */
    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询品牌推荐
     */
    Page<HomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNo);
}
