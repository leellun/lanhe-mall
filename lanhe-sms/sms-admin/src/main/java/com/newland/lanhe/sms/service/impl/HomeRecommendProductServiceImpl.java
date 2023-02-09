package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.HomeNewProduct;
import com.newland.lanhe.sms.entity.HomeRecommendProduct;
import com.newland.lanhe.sms.mapper.HomeRecommendProductMapper;
import com.newland.lanhe.sms.service.HomeRecommendProductService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class HomeRecommendProductServiceImpl extends ServiceImpl<HomeRecommendProductMapper, HomeRecommendProduct> implements HomeRecommendProductService {
    @Override
    public void create(List<HomeRecommendProduct> homeRecommendProductList) {
        for (HomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
        }
        saveBatch(homeRecommendProductList);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeRecommendProduct recommendProduct = new HomeRecommendProduct();
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
        HomeRecommendProduct record = new HomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);
        baseMapper.update(record, Wrappers.<HomeRecommendProduct>lambdaUpdate().in(HomeRecommendProduct::getId,ids));
    }

    @Override
    public Page<HomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNo) {
        LambdaQueryWrapper<HomeRecommendProduct> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(productName)) {
            queryWrapper.like(HomeRecommendProduct::getProductName, productName);
        }
        if (recommendStatus != null) {
            queryWrapper.eq(HomeRecommendProduct::getRecommendStatus, recommendStatus);
        }
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return baseMapper.selectPage(PageWrapper.wrapper(pageEntity), queryWrapper);
    }
}
