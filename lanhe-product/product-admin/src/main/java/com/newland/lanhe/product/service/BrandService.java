package com.newland.lanhe.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.product.entity.Brand;
import com.newland.lanhe.product.model.dto.BrandDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface BrandService extends IService<Brand> {
    /**
     * 获取所有品牌
     */
    List<Brand> listAllBrand();

    /**
     * 创建品牌
     */
    void createBrand(BrandDto BrandParam);

    /**
     * 修改品牌
     */
    void updateBrand(Long id, BrandDto BrandParam);

    /**
     * 删除品牌
     */
    void deleteBrand(Long id);

    /**
     * 批量删除品牌
     */
    void deleteBrand(List<Long> ids);

    /**
     * 分页查询品牌
     */
    Page<Brand> listBrand(String keyword, int pageNo, int pageSize);

    /**
     * 获取品牌
     */
    Brand getBrand(Long id);

    /**
     * 修改显示状态
     */
    void updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 修改厂家制造商状态
     */
    void updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
