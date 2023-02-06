package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.HomeAdvertise;
import com.newland.lanhe.sms.entity.HomeBrand;
import com.newland.lanhe.sms.mapper.HomeBrandMapper;
import com.newland.lanhe.sms.service.HomeBrandService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class HomeBrandServiceImpl extends ServiceImpl<HomeBrandMapper, HomeBrand> implements HomeBrandService {
    @Override
    public void create(List<HomeBrand> homeBrandList) {
        for (HomeBrand HomeBrand : homeBrandList) {
            HomeBrand.setRecommendStatus(1);
            HomeBrand.setSort(0);
        }
        saveBatch(homeBrandList);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeBrand homeBrand = new HomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        baseMapper.updateById(homeBrand);
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        HomeBrand record = new HomeBrand();
        record.setRecommendStatus(recommendStatus);
        baseMapper.update(record, Wrappers.<HomeBrand>lambdaUpdate().in(HomeBrand::getId,ids));
    }

    @Override
    public Page<HomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<HomeBrand> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(brandName)) {
            queryWrapper.like(HomeBrand::getBrandName, brandName);
        }
        if (recommendStatus != null) {
            queryWrapper.eq(HomeBrand::getRecommendStatus, recommendStatus);
        }
        PageEntity pageEntity = PageEntity.page(pageNum, pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return baseMapper.selectPage(PageWrapper.wrapper(pageEntity), queryWrapper);
    }
}
