package com.newland.lanhe.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.security.utils.SecurityUtil;
import com.newland.lanhe.user.dto.LoginDTO;
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
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
        Page<SysUser> page = PageWrapper.wrapper(userQueryDTO);
        return baseMapper.selectPage(page, Wrappers.lambdaQuery());
    }

    @Override
    public void addUser(SysUser sysUser) {
        SysUser dbUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, sysUser.getUsername()));
        AssertUtil.isNull(dbUser, UserServiceErrorEnum.USER_EXIST);
        baseMapper.insert(sysUser);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        SysUser dbUser = baseMapper.selectById(sysUser.getId());
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST);
        sysUser.setId(dbUser.getId());
        baseMapper.updateById(sysUser);
    }

    @Override
    public void updateCenter(UserCenterDTO userCenterDTO) {
        SysUser dbUser = baseMapper.selectById(userCenterDTO.getId());
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST);
        baseMapper.update(null, Wrappers.<SysUser>lambdaUpdate()
                .set(SysUser::getNickName, userCenterDTO.getNickName())
                .set(SysUser::getPhone, userCenterDTO.getPhone())
                .set(SysUser::getGender, userCenterDTO.getGender())
                .eq(SysUser::getId, userCenterDTO.getId())
        );
    }

    @Override
    public void deleteUser(Set<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.USER_DELETE_FAIL);
    }

    @Override
    public void updatePass(UserPassVO userPassVO) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        SysUser dbUser = baseMapper.selectById(loginUser.getUserId());
        String oldMd5Password = MD5.encrypt(AesUtils.decrypt(userPassVO.getOldPass()));
        AssertUtil.isNull(dbUser.getPassword().equals(oldMd5Password), UserServiceErrorEnum.USER_OLD_PASSWORD_ERROR);
        String md5Password = MD5.encrypt(AesUtils.decrypt(userPassVO.getNewPass()));
        baseMapper.update(null, Wrappers.<SysUser>lambdaUpdate()
                .set(SysUser::getPassword, md5Password)
                .eq(SysUser::getId, loginUser.getUserId())
        );
    }

    @Override
    public void resetPass(UserResetPassDTO userResetPassDTO) {
        SysUser dbUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, userResetPassDTO.getUsername()));
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST);
        String md5Password = MD5.encrypt(AesUtils.decrypt(userResetPassDTO.getNewPass()));
        baseMapper.update(null, Wrappers.<SysUser>lambdaUpdate()
                .set(SysUser::getPassword, md5Password)
                .eq(SysUser::getId, dbUser.getId())
        );
    }

    @Override
    public void updateAvatar(String avatar) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        SysUser dbUser = baseMapper.selectById(loginUser.getUserId());
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST);
        baseMapper.update(null, Wrappers.<SysUser>lambdaUpdate()
                .set(SysUser::getAvatar, avatar)
                .eq(SysUser::getId, dbUser.getId())
        );
    }
}
