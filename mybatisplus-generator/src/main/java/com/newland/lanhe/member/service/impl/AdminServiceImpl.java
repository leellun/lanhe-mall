package com.newland.lanhe.member.service.impl;

import com.newland.lanhe.member.entity.Admin;
import com.newland.lanhe.member.mapper.AdminMapper;
import com.newland.lanhe.member.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
