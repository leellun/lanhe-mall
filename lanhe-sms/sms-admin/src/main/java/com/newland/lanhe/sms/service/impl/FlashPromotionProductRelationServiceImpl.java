package com.newland.lanhe.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.enumeration.ResultCode;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.agent.ProductAgentApi;
import com.newland.lanhe.product.vo.ProductOutputVo;
import com.newland.lanhe.sms.entity.FlashPromotionProductRelation;
import com.newland.lanhe.sms.mapper.FlashPromotionProductRelationMapper;
import com.newland.lanhe.sms.model.vo.FlashPromotionProductVo;
import com.newland.lanhe.sms.service.FlashPromotionProductRelationService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class FlashPromotionProductRelationServiceImpl extends ServiceImpl<FlashPromotionProductRelationMapper, FlashPromotionProductRelation> implements FlashPromotionProductRelationService {
    @Autowired
    private ProductAgentApi productAgentApi;

    @Override
    public void create(List<FlashPromotionProductRelation> relationList) {
        saveBatch(relationList);
    }

    @Override
    public void update(Long id, FlashPromotionProductRelation relation) {
        relation.setId(id);
        baseMapper.updateById(relation);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public FlashPromotionProductRelation getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Page<FlashPromotionProductVo> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNo) {
        LambdaQueryWrapper<FlashPromotionProductRelation> wrapper = Wrappers.<FlashPromotionProductRelation>lambdaQuery().eq(FlashPromotionProductRelation::getFlashPromotionId, flashPromotionId);
        if (flashPromotionSessionId != null) {
            wrapper.eq(FlashPromotionProductRelation::getFlashPromotionSessionId, flashPromotionSessionId);
        }
        Page<FlashPromotionProductRelation> page = baseMapper.selectPage(PageWrapper.wrapper(PageEntity.page(pageNo, pageSize)), wrapper);
        Page<FlashPromotionProductVo> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        List<FlashPromotionProductVo> list = new ArrayList<>();
        for (FlashPromotionProductRelation flashPromotionProductRelation : page.getRecords()) {
            FlashPromotionProductVo flashPromotionProductVo = new FlashPromotionProductVo();
            BeanUtils.copyProperties(flashPromotionProductRelation, flashPromotionProductVo);
            RestResponse<ProductOutputVo> response = productAgentApi.getProductByProductId(flashPromotionProductRelation.getProductId());
            if (response.getCode().equals(ResultCode.SUCCESS.getCode())) {
                flashPromotionProductVo.setProduct(response.getData());
            }
            list.add(flashPromotionProductVo);
        }
        result.setRecords(list);
        return result;
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return baseMapper.selectCount(Wrappers.<FlashPromotionProductRelation>lambdaQuery()
                .eq(FlashPromotionProductRelation::getFlashPromotionId, flashPromotionId)
                .eq(FlashPromotionProductRelation::getFlashPromotionSessionId, flashPromotionSessionId));
    }
}
