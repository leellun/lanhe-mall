package com.newland.lanhe.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.model.ErrorEntity;
import com.newland.lanhe.product.entity.SkuStock;
import com.newland.lanhe.product.mapper.SkuStockMapper;
import com.newland.lanhe.product.service.SkuStockService;
import com.newland.lanhe.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {
    @Autowired
    private SkuStockMapper skuStockMapper;

    @Override
    public List<SkuStock> getList(Long pid, String keyword) {
        LambdaQueryWrapper<SkuStock> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like(SkuStock::getSkuCode, "%" + keyword + "%");
        }
        return skuStockMapper.selectList(queryWrapper);
    }

    @Override
    public void update(Long pid, List<SkuStock> skuStockList) {
        boolean result = saveOrUpdateBatch(skuStockList);
        AssertUtil.isTrue(result, ErrorEntity.error("更新失敗"));
    }
}