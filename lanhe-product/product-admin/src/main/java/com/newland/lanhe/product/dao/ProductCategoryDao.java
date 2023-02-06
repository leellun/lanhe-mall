package com.newland.lanhe.product.dao;

import com.newland.lanhe.product.model.vo.ProductCategoryWithChildrenItemVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类自定义Dao
 */
@Repository
public interface ProductCategoryDao {
    /**
     * 获取商品分类及其子分类
     */
    List<ProductCategoryWithChildrenItemVo> listWithChildren();
}
