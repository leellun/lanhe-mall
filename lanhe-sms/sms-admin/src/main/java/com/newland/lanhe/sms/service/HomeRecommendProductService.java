package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.HomeRecommendProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface HomeRecommendProductService extends IService<HomeRecommendProduct> {
    /**
     * 添加首页推荐
     */
    void create(List<HomeRecommendProduct> homeRecommendProductList);

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
    Page<HomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
