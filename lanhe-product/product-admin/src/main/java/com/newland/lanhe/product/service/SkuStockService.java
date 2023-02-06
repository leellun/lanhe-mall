package com.newland.lanhe.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.product.entity.SkuStock;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface SkuStockService extends IService<SkuStock> {
    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<SkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    void update(Long pid, List<SkuStock> skuStockList);
}
