package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.HomeNewProduct;
import com.newland.lanhe.sms.mapper.HomeNewProductMapper;
import com.newland.lanhe.sms.service.HomeNewProductService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class HomeNewProductServiceImpl extends ServiceImpl<HomeNewProductMapper, HomeNewProduct> implements HomeNewProductService {
    @Override
    public void create(List<HomeNewProduct> homeNewProductList) {
        for (HomeNewProduct homeNewProduct : homeNewProductList) {
            homeNewProduct.setRecommendStatus(1);
            homeNewProduct.setSort(0);
        }
        saveBatch(homeNewProductList);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeNewProduct homeNewProduct = new HomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        baseMapper.updateById(homeNewProduct);
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        HomeNewProduct record = new HomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        baseMapper.update(record, Wrappers.<HomeNewProduct>lambdaUpdate().in(HomeNewProduct::getId,ids));
    }

    @Override
    public Page<HomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNo) {
        LambdaQueryWrapper<HomeNewProduct> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(productName)) {
            queryWrapper.like(HomeNewProduct::getProductName, productName);
        }
        if (recommendStatus != null) {
            queryWrapper.eq(HomeNewProduct::getRecommendStatus, recommendStatus);
        }
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder("sort");
        pageEntity.setDesc(true);
        return baseMapper.selectPage(PageWrapper.wrapper(pageEntity), queryWrapper);
    }
}
