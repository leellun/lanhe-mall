package com.newland.lanhe.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.enums.UserServiceErrorEnum;
import com.newland.lanhe.user.mapper.SysMenuMapper;
import com.newland.lanhe.user.mapper.SysRoleMapper;
import com.newland.lanhe.user.mapper.SysUserMapper;
import com.newland.lanhe.user.model.dto.UserCenterDTO;
import com.newland.lanhe.user.model.dto.UserPassVO;
import com.newland.lanhe.user.model.dto.UserQueryDTO;
import com.newland.lanhe.user.model.dto.UserResetPassDTO;
import com.newland.lanhe.user.service.SysMenuService;
import com.newland.lanhe.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.utils.AesUtils;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.lanhe.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public LoginUser login(LoginDTO loginDTO) {
        String password = AesUtils.decrypt(loginDTO.getPassword());
        String md5Password = MD5.encrypt(password);
        SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, loginDTO.getUsername()));
        AssertUtil.notNull(sysUser, UserServiceErrorEnum.USER_NOT_EXIST);
        AssertUtil.isTrue(md5Password.endsWith(sysUser.getPassword()), UserServiceErrorEnum.USER_PASSWORD_ERROR);
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(sysUser.getUsername());
        loginUser.setMobile(sysUser.getPhone());
        loginUser.setDepartmentId(sysUser.getDeptId());
        loginUser.setUserId(sysUser.getId());
        loginUser.setAuthorities(sysMenuService.getPermissions(sysUser.getId()));
        return loginUser;
    }

    @Override
    public Page<SysUser> getUsers(UserQueryDTO userQueryDTO) {
        return null;
    }

    @Override
    public void addUser(SysUser sysUser) {

    }

    @Override
    public void updateUser(SysUser sysUser) {

    }

    @Override
    public void updateCenter(UserCenterDTO userCenterDTO) {

    }

    @Override
    public void deleteUser(Set<Long> ids) {

    }

    @Override
    public void updatePass(UserPassVO userPassVO) {

    }

    @Override
    public void resetPass(UserResetPassDTO userResetPassDTO) {

    }

    @Override
    public void updateAvatar(String avatar) {

    }
}
