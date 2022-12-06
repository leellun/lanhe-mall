package com.newland.lanhe.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import com.newland.lanhe.user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.user.model.dto.ResetPwdDto;
import com.newland.lanhe.user.model.vo.SysUserVo;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
public interface SysUserService extends IService<SysUser> {

    public LoginUser login(LoginDTO loginDTO);

    void loginOut(LoginUser loginUser);

    void resetPwd(LoginUser user, ResetPwdDto resetPwdDto);

    Page<SysUser> getList(Integer pageNo, Integer pageSize);
}
