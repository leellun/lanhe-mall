package com.newland.lanhe.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.member.entity.MemberLevel;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
public interface MemberLevelService extends IService<MemberLevel> {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<MemberLevel> list(Integer defaultStatus);

    /**
     * 获取所有会员等级，通过增长点排序
     * @return
     */
    List<MemberLevel> getAllMemberLevels();
}
