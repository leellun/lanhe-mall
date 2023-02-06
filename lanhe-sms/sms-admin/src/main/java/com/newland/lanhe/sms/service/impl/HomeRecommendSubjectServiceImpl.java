package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.HomeRecommendSubject;
import com.newland.lanhe.sms.mapper.HomeRecommendSubjectMapper;
import com.newland.lanhe.sms.service.HomeRecommendSubjectService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class HomeRecommendSubjectServiceImpl extends ServiceImpl<HomeRecommendSubjectMapper, HomeRecommendSubject> implements HomeRecommendSubjectService {
    @Override
    public void create(List<HomeRecommendSubject> recommendSubjectList) {
        for (HomeRecommendSubject recommendProduct : recommendSubjectList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
        }
        saveBatch(recommendSubjectList);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeRecommendSubject recommendProduct = new HomeRecommendSubject();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        baseMapper.updateById(recommendProduct);
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        HomeRecommendSubject record = new HomeRecommendSubject();
        record.setRecommendStatus(recommendStatus);
        baseMapper.update(record, Wrappers.<HomeRecommendSubject>lambdaUpdate().in(HomeRecommendSubject::getId, ids));
    }

    @Override
    public Page<HomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<HomeRecommendSubject> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(subjectName)) {
            queryWrapper.like(HomeRecommendSubject::getSubjectName, subjectName);
        }
        if (recommendStatus != null) {
            queryWrapper.eq(HomeRecommendSubject::getRecommendStatus, recommendStatus);
        }
        PageEntity pageEntity = PageEntity.page(pageNum, pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return baseMapper.selectPage(PageWrapper.wrapper(pageEntity), queryWrapper);
    }
}
