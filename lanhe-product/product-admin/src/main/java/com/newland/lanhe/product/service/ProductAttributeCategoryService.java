package com.newland.lanhe.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.product.entity.ProductAttributeCategory;
import com.newland.lanhe.product.model.vo.ProductAttributeCategoryItemVo;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {
    /**
     * 创建属性分类
     */
    void create(String name);

    /**
     * 修改属性分类
     */
    void update(Long id, String name);

    /**
     * 删除属性分类
     */
    void delete(Long id);

    /**
     * 获取属性分类详情
     */
    ProductAttributeCategory getItem(Long id);

    /**
     * 分页查询属性分类
     */
    Page<ProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 获取包含属性的属性分类
     */
    List<ProductAttributeCategoryItemVo> getListWithAttr();
}
