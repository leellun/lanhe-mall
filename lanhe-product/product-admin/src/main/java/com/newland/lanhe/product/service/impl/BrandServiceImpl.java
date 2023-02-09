package com.newland.lanhe.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.product.entity.Brand;
import com.newland.lanhe.product.entity.Product;
import com.newland.lanhe.product.mapper.BrandMapper;
import com.newland.lanhe.product.mapper.ProductMapper;
import com.newland.lanhe.product.model.dto.BrandDto;
import com.newland.lanhe.product.service.BrandService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Brand> listAllBrand() {
        return baseMapper.selectList(Wrappers.<Brand>lambdaQuery().orderByDesc(Brand::getSort));
    }

    @Override
    public void createBrand(BrandDto brandDto) {
        Brand Brand = new Brand();
        BeanUtils.copyProperties(brandDto, Brand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(Brand.getFirstLetter())) {
            Brand.setFirstLetter(Brand.getName().substring(0, 1));
        }
        baseMapper.insert(Brand);
    }

    @Override
    public void updateBrand(Long id, BrandDto brandDto) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);
        brand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        productMapper.update(null, Wrappers.<Product>lambdaUpdate().set(Product::getBrandName, brand.getName()).eq(Product::getBrandId, id));
        baseMapper.updateById(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void deleteBrand(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Page<Brand> listBrand(String keyword, int pageNo, int pageSize) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        Page<Brand> wrapper = PageWrapper.wrapper(pageEntity);
        LambdaQueryWrapper<Brand> queryWrapper = Wrappers.<Brand>lambdaQuery();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like(Brand::getName, keyword);
        }
        return baseMapper.selectPage(wrapper, queryWrapper);
    }


    @Override
    public Brand getBrand(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void updateShowStatus(List<Long> ids, Integer showStatus) {
        baseMapper.update(new Brand(), Wrappers.<Brand>lambdaUpdate().set(Brand::getShowStatus, showStatus).in(Brand::getId, ids));
    }

    @Override
    public void updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        baseMapper.update(new Brand(), Wrappers.<Brand>lambdaUpdate().set(Brand::getFactoryStatus, factoryStatus).in(Brand::getId, ids));
    }
}
