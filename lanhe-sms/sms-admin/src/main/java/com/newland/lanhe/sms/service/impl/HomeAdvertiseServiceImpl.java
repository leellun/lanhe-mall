package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.HomeAdvertise;
import com.newland.lanhe.sms.mapper.HomeAdvertiseMapper;
import com.newland.lanhe.sms.service.HomeAdvertiseService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertise> implements HomeAdvertiseService {
    @Override
    public void create(HomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        baseMapper.insert(advertise);
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        HomeAdvertise record = new HomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        baseMapper.updateById(record);
    }

    @Override
    public HomeAdvertise getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void update(Long id, HomeAdvertise advertise) {
        advertise.setId(id);
        baseMapper.updateById(advertise);
    }

    @Override
    public Page<HomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNo) {
        LambdaQueryWrapper<HomeAdvertise> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(HomeAdvertise::getName, name);
        }
        if (type != null) {
            queryWrapper.eq(HomeAdvertise::getType, type);
        }
        if (!StringUtils.isEmpty(endTime)) {
            try {
                LocalDateTime startDateTime = LocalDateTime.parse(endTime);
                LocalDateTime endDateTime = LocalDateTime.parse(endTime).plusDays(1);
                queryWrapper.between(HomeAdvertise::getEndTime, startDateTime, endDateTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return baseMapper.selectPage(PageWrapper.wrapper(pageEntity), queryWrapper);
    }
}
