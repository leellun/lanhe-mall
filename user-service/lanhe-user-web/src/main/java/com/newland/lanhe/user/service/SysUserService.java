package com.newland.lanhe.user.service;

import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
public interface SysUserService extends IService<SysUser> {

    LoginUser login(LoginDTO loginDTO);
}
