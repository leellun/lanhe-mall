package com.newland.lanhe.product.dao;

import com.newland.lanhe.product.model.vo.ProductAttributeCategoryItemVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自定义商品属性分类Dao
 */
@Repository
public interface ProductAttributeCategoryDao {
    /**
     * 获取包含属性的商品属性分类
     */
    List<ProductAttributeCategoryItemVo> getListWithAttr();
}
