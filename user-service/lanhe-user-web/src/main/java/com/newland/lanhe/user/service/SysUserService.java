package com.newland.lanhe.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.model.dto.UserCenterDTO;
import com.newland.lanhe.user.model.dto.UserPassVO;
import com.newland.lanhe.user.model.dto.UserQueryDTO;
import com.newland.lanhe.user.model.dto.UserResetPassDTO;

import java.util.Set;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    LoginUser login(LoginDTO loginDTO);

    /**
     * 分页查询
     * @param userQueryDTO
     * @return
     */
    Page<SysUser> getUsers(UserQueryDTO userQueryDTO);

    /**
     * 添加用户
     * @param sysUser
     */
    void addUser(SysUser sysUser);

    /**
     * 更新用户
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 个人中心修改
     * @param userCenterDTO
     */
    void updateCenter(UserCenterDTO userCenterDTO);

    /**
     * 删除用户
     * @param ids
     */
    void deleteUser(Set<Long> ids);

    /**
     * 更新密码
     * @param userPassVO
     */
    void updatePass(UserPassVO userPassVO);

    /**
     * 重置密码
     * @param userResetPassDTO
     */
    void resetPass(UserResetPassDTO userResetPassDTO);

    /**
     * 更新头像
     * @param avatar
     */
    void updateAvatar(String avatar);
}
