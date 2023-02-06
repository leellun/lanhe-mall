package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.FlashPromotion;
import com.newland.lanhe.sms.mapper.FlashPromotionMapper;
import com.newland.lanhe.sms.service.FlashPromotionService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class FlashPromotionServiceImpl extends ServiceImpl<FlashPromotionMapper, FlashPromotion> implements FlashPromotionService {

    @Override
    public void create(FlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(LocalDateTime.now());
        baseMapper.insert(flashPromotion);
    }

    @Override
    public void update(Long id, FlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        baseMapper.updateById(flashPromotion);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        FlashPromotion flashPromotion = new FlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        baseMapper.updateById(flashPromotion);
    }

    @Override
    public FlashPromotion getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Page<FlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<FlashPromotion> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like(FlashPromotion::getTitle, keyword);
        }
        return baseMapper.selectPage(PageWrapper.wrapper(PageEntity.page(pageNum, pageSize)), queryWrapper);
    }
}
