package com.newland.lanhe.product.dao;

import com.newland.lanhe.product.model.vo.ProductAttrInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自定义商品属性Dao
 */
@Repository
public interface ProductAttributeDao {
    /**
     * 获取商品属性信息
     */
    List<ProductAttrInfoVo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
