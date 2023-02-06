package com.newland.lanhe.member.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.member.entity.MemberLevel;
import com.newland.lanhe.member.mapper.MemberLevelMapper;
import com.newland.lanhe.member.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {
    @Override
    public List<MemberLevel> list(Integer defaultStatus) {
        return baseMapper.selectList(Wrappers.<MemberLevel>lambdaQuery().eq(MemberLevel::getDefaultStatus, defaultStatus));
    }

    @Override
    public List<MemberLevel> getAllMemberLevels() {
        return baseMapper.selectList(Wrappers.<MemberLevel>lambdaQuery().orderByAsc(MemberLevel::getGrowthPoint));
    }
}
