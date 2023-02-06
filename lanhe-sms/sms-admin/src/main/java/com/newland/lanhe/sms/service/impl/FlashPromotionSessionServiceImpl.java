package com.newland.lanhe.sms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.sms.entity.FlashPromotionSession;
import com.newland.lanhe.sms.mapper.FlashPromotionSessionMapper;
import com.newland.lanhe.sms.model.vo.FlashPromotionSessionDetailVo;
import com.newland.lanhe.sms.service.FlashPromotionProductRelationService;
import com.newland.lanhe.sms.service.FlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class FlashPromotionSessionServiceImpl extends ServiceImpl<FlashPromotionSessionMapper, FlashPromotionSession> implements FlashPromotionSessionService {
    @Autowired
    private FlashPromotionProductRelationService relationService;

    @Override
    public void create(FlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(LocalDateTime.now());
        baseMapper.insert(promotionSession);
    }

    @Override
    public void update(Long id, FlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        baseMapper.updateById(promotionSession);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        FlashPromotionSession promotionSession = new FlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        baseMapper.updateById(promotionSession);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public FlashPromotionSession getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<FlashPromotionSession> list() {
        return baseMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public List<FlashPromotionSessionDetailVo> selectList(Long flashPromotionId) {
        List<FlashPromotionSessionDetailVo> result = new ArrayList<>();
        List<FlashPromotionSession> list = baseMapper.selectList(Wrappers.<FlashPromotionSession>lambdaQuery().eq(FlashPromotionSession::getStatus,1));
        for (FlashPromotionSession promotionSession : list) {
            FlashPromotionSessionDetailVo detail = new FlashPromotionSessionDetailVo();
            BeanUtils.copyProperties(promotionSession, detail);
            long count = relationService.getCount(flashPromotionId, promotionSession.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }
}
