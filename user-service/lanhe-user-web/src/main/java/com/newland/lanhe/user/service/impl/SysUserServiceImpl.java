package com.newland.lanhe.user.service.impl;

import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.enumeration.BasicEnum;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.enums.UserServiceErrorEnum;
import com.newland.lanhe.user.mapper.SysUserMapper;
import com.newland.lanhe.user.model.dto.ResetPwdDto;
import com.newland.lanhe.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.utils.AesUtils;
import com.newland.lanhe.utils.AssertUtil;
import org.springframework.stereotype.Service;

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

    @Override
    public LoginUser login(LoginDTO loginDTO) {
        SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, loginDTO.getUsername()));
        AssertUtil.notNull(sysUser, UserServiceErrorEnum.USER_NOT_EXIST);
        String password = AesUtils.decrypt(loginDTO.getPassword());
        String md5Password = MD5Utils.encodeHexString(password.getBytes());
        AssertUtil.isTrue(sysUser.getPassword().equals(md5Password), UserServiceErrorEnum.USER_PASSWORD_ERROR);
        if (!sysUser.getPassword().equals(md5Password)) {
            baseMapper.update(null, Wrappers.<SysUser>lambdaUpdate().set(SysUser::getPwdFailsCount, sysUser.getPwdFailsCount() + 1).eq(SysUser::getId, sysUser.getId()));
        } else if (sysUser.getPwdFailsCount() > 0) {
            baseMapper.update(null, Wrappers.<SysUser>lambdaUpdate().set(SysUser::getPwdFailsCount, 0).set(SysUser::getFailLockTime, null).eq(SysUser::getId, sysUser.getId()));
        }
        AssertUtil.isTrue(sysUser.getEnabled().equals(BasicEnum.YES.getCode()), UserServiceErrorEnum.USER_FORBIDDEN);
        LoginUser loginUser=new LoginUser();
        loginUser.setUserId(sysUser.getId());
        loginUser.setUsername(sysUser.getUsername());
        loginUser.setDepartmentId(sysUser.getDeptId());
        loginUser.setMobile(sysUser.getPhone());
        return loginUser;
    }

    @Override
    public void loginOut(LoginUser loginUser) {

    }

    @Override
    public void resetPwd(LoginUser user, ResetPwdDto resetPwdDto) {

    }

    @Override
    public Page<SysUser> getList(Integer pageNo, Integer pageSize) {
        return null;
    }
}
