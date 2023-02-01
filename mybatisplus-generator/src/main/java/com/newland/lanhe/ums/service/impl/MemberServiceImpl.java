package com.newland.lanhe.ums.service.impl;

import com.newland.lanhe.ums.entity.Member;
import com.newland.lanhe.ums.mapper.MemberMapper;
import com.newland.lanhe.ums.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-01-31
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
