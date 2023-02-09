package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.Coupon;
import com.newland.lanhe.sms.entity.CouponProductCategoryRelation;
import com.newland.lanhe.sms.entity.CouponProductRelation;
import com.newland.lanhe.sms.mapper.CouponMapper;
import com.newland.lanhe.sms.mapper.CouponProductCategoryRelationMapper;
import com.newland.lanhe.sms.mapper.CouponProductRelationMapper;
import com.newland.lanhe.sms.model.dto.CouponDto;
import com.newland.lanhe.sms.service.CouponProductCategoryRelationService;
import com.newland.lanhe.sms.service.CouponProductRelationService;
import com.newland.lanhe.sms.service.CouponService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
    @Autowired
    private CouponProductRelationMapper productRelationMapper;
    @Autowired
    private CouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Autowired
    private CouponProductRelationService couponProductRelationService;
    @Autowired
    private CouponProductCategoryRelationService couponProductCategoryRelationService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CouponDto couponDto) {
        couponDto.setCount(couponDto.getPublishCount());
        couponDto.setUseCount(0);
        couponDto.setReceiveCount(0);
        //插入优惠券表
        baseMapper.insert(couponDto);
        //插入优惠券和商品关系表
        if (couponDto.getUseType().equals(2)) {
            couponDto.getProductRelationList().forEach(item->item.setCouponId(couponDto.getId()));
            couponProductRelationService.saveBatch(couponDto.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if (couponDto.getUseType().equals(1)) {
            couponDto.getProductCategoryRelationList().forEach(item->item.setCouponId(couponDto.getId()));
            couponProductCategoryRelationService.saveBatch(couponDto.getProductCategoryRelationList());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //删除优惠券
        baseMapper.deleteById(id);
        //删除商品关联
        productRelationMapper.delete(Wrappers.<CouponProductRelation>lambdaQuery().eq(CouponProductRelation::getCouponId,id));
        //删除商品分类关联
        productCategoryRelationMapper.delete(Wrappers.<CouponProductCategoryRelation>lambdaQuery().eq(CouponProductCategoryRelation::getCouponId,id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, CouponDto couponDto) {
        couponDto.setId(id);
        baseMapper.updateById(couponDto);
        //删除后插入优惠券和商品关系表
        if (couponDto.getUseType().equals(2)) {
            //删除商品关联
            productRelationMapper.delete(Wrappers.<CouponProductRelation>lambdaQuery().eq(CouponProductRelation::getCouponId,id));
            couponDto.getProductRelationList().forEach(item->item.setCouponId(couponDto.getId()));
            couponProductRelationService.saveBatch(couponDto.getProductRelationList());
        }
        //删除后插入优惠券和商品分类关系表
        if (couponDto.getUseType().equals(1)) {
            //删除商品分类关联
            productCategoryRelationMapper.delete(Wrappers.<CouponProductCategoryRelation>lambdaQuery().eq(CouponProductCategoryRelation::getCouponId,id));
            couponDto.getProductCategoryRelationList().forEach(item->item.setCouponId(couponDto.getId()));
            couponProductCategoryRelationService.saveBatch(couponDto.getProductCategoryRelationList());
        }
    }

    @Override
    public Page<Coupon> list(String name, Integer type, Integer pageSize, Integer pageNo) {
        LambdaQueryWrapper<Coupon> queryWrapper=Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(Coupon::getName,name);
        }
        if (type != null) {
            queryWrapper.eq(Coupon::getType,type);
        }
        return baseMapper.selectPage(PageWrapper.wrapper(PageEntity.page(pageNo,pageSize)),queryWrapper);
    }

    @Override
    public CouponDto getItem(Long id) {
        CouponDto couponDto = new CouponDto();
        Coupon coupon = baseMapper.selectById(id);
        BeanUtils.copyProperties(coupon, couponDto);
        List<CouponProductRelation> productRelationList = productRelationMapper.selectList(Wrappers.<CouponProductRelation>lambdaQuery().eq(CouponProductRelation::getCouponId, id));
        List<CouponProductCategoryRelation> productCategoryRelationList = productCategoryRelationMapper.selectList(Wrappers.<CouponProductCategoryRelation>lambdaQuery().eq(CouponProductCategoryRelation::getCouponId, id));
        couponDto.setProductCategoryRelationList(productCategoryRelationList);
        couponDto.setProductRelationList(productRelationList);
        return couponDto;
    }
}
