package com.newland.lanhe.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.sms.entity.HomeAdvertise;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
public interface HomeAdvertiseService extends IService<HomeAdvertise> {
    /**
     * 添加广告
     */
    void create(HomeAdvertise advertise);

    /**
     * 批量删除广告
     */
    void delete(List<Long> ids);

    /**
     * 修改上、下线状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 获取广告详情
     */
    HomeAdvertise getItem(Long id);

    /**
     * 更新广告
     */
    void update(Long id, HomeAdvertise advertise);

    /**
     * 分页查询广告
     */
    Page<HomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNo);
}
