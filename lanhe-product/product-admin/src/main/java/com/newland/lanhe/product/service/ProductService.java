package com.newland.lanhe.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.product.entity.Product;
import com.newland.lanhe.product.model.dto.ProductDto;
import com.newland.lanhe.product.model.dto.ProductQueryDto;
import com.newland.lanhe.product.model.vo.ProductDetailVo;
import com.newland.lanhe.product.vo.ProductOutputVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface ProductService extends IService<Product> {
    /**
     * 获取商品列表
     * @param productQueryDto
     * @return
     */
    Page<Product> list(ProductQueryDto productQueryDto);

    /**
     * 获取详情
     * @param id
     * @return
     */
    ProductDetailVo getProductDetail(Long id);

    /**
     * 添加商品
     * @param productParam
     */
    void add(ProductDto productParam);

    /**
     * 更新商品
     * @param id
     * @param productDto
     */
    void update(Long id, ProductDto productDto);
    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);
    /**
     * 批量修改商品上架状态
     */
    void updatePublishStatus(List<Long> ids, Integer publishStatus);
    /**
     * 批量修改商品推荐状态
     */
    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);
    /**
     * 批量修改新品状态
     */
    void updateNewStatus(List<Long> ids, Integer newStatus);
    /**
     * 批量修改审核状态
     * @param ids 产品id
     * @param verifyStatus 审核状态
     * @param detail 审核详情
     */
    void updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<Product> list(String keyword);

    /**
     * 根据 商品 id 过去商品信息
     * @param productId
     * @return
     */
    ProductOutputVo getProductByProductId(Long productId);
}
