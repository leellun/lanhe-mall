package com.newland.lanhe.member.service.impl;

import com.newland.lanhe.member.entity.Member;
import com.newland.lanhe.member.mapper.MemberMapper;
import com.newland.lanhe.member.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
