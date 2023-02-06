package com.newland.lanhe.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.product.entity.ProductAttribute;
import com.newland.lanhe.product.model.dto.ProductAttributeDto;
import com.newland.lanhe.product.model.vo.ProductAttrInfoVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface ProductAttributeService extends IService<ProductAttribute> {
    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性；2->参数
     */
    Page<ProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取属性分类下所有属性，包含规格属性和销售属性
     * @param cid
     * @return
     */
    List<ProductAttribute> getList(Long cid, Integer type);
    /**
     * 添加商品属性
     */
    void create(ProductAttributeDto productAttributeDto);

    /**
     * 修改商品属性
     */
    void update(Long id, ProductAttributeDto productAttributeDto);

    /**
     * 获取单个商品属性信息
     */
    ProductAttribute getItem(Long id);

    /**
     * 删除属性
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 获取产品属性
     * @param productCategoryId
     * @return
     */
    List<ProductAttrInfoVo> getProductAttrInfo(Long productCategoryId);

}
