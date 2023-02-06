package com.newland.lanhe.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.product.entity.ProductCategory;
import com.newland.lanhe.product.model.dto.ProductCategoryDto;
import com.newland.lanhe.product.model.vo.ProductCategoryWithChildrenItemVo;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    /**
     * 创建商品分类
     */
    void create(ProductCategoryDto productCategoryDto);

    /**
     * 修改商品分类
     */
    void update(Long id, ProductCategoryDto productCategoryDto);

    /**
     * 分页获取商品分类
     */
    Page<ProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除商品分类
     */
    void delete(Long id);

    /**
     * 根据ID获取商品分类
     */
    ProductCategory getItem(Long id);

    /**
     * 批量修改导航状态
     */
    void updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     */
    void updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     */
    List<ProductCategoryWithChildrenItemVo> listWithChildren();

    /**
     * 获取子类
     * @param pid
     * @return
     */
    List<ProductCategory> getSubCategorys(Long pid);
}
