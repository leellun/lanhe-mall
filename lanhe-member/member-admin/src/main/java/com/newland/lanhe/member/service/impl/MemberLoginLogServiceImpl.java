package com.newland.lanhe.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.member.entity.MemberLoginLog;
import com.newland.lanhe.member.mapper.MemberLoginLogMapper;
import com.newland.lanhe.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员登录记录 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Service
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog> implements MemberLoginLogService {

}
