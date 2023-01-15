package com.newland.lanhe.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.entity.SysUserRole;
import com.newland.lanhe.user.enums.UserServiceErrorEnum;
import com.newland.lanhe.user.mapper.SysRoleMapper;
import com.newland.lanhe.user.mapper.SysUserMapper;
import com.newland.lanhe.user.model.dto.LevelRoleDTO;
import com.newland.lanhe.user.model.dto.RoleQueryDTO;
import com.newland.lanhe.user.model.dto.UserRoleDTO;
import com.newland.lanhe.user.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.user.service.SysUserRoleService;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysRole getRole(Long id) {
        SysRole sysRole = baseMapper.selectById(id);
        AssertUtil.notNull(sysRole, UserServiceErrorEnum.ROLE_NOT_EXIST);
        return sysRole;
    }

    @Override
    public void addRole(SysRole sysRole) {
        SysRole dbRole = baseMapper.selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getName, sysRole.getName()));
        AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_NAME_EXIST);
        dbRole = baseMapper.selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getCode, sysRole.getCode()));
        AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_CODE_EXIST);
        baseMapper.insert(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        SysRole role = baseMapper.selectById(sysRole.getId());
        AssertUtil.notNull(role, UserServiceErrorEnum.ROLE_NOT_EXIST);
        if (!sysRole.getName().equals(role.getName())) {
            SysRole dbRole = baseMapper.selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getName, sysRole.getName()));
            AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_NAME_EXIST);
        }
        if (!sysRole.getCode().equals(role.getCode())) {
            SysRole dbRole = baseMapper.selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getCode, sysRole.getCode()));
            AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_CODE_EXIST);
        }
        baseMapper.updateById(sysRole);
    }

    @Override
    public void deleteRoles(Set<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        AssertUtil.isNull(count > 0, UserServiceErrorEnum.ROLE_DELETE_FAIL);
    }

    @Override
    public void addUserBinds(List<UserRoleDTO> list) {
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : list) {
            Long roleId = userRoleDTO.getRoleId();
            SysUserRole userRoleDO = new SysUserRole(userRoleDTO.getUserId(), roleId);
            sysUserRoles.add(userRoleDO);
        }
        sysUserRoleService.saveBatch(sysUserRoles);
    }

    @Override
    public void deleteUsers(List<UserRoleDTO> list) {
        for (UserRoleDTO roleDTO : list) {
            Long roleId = roleDTO.getRoleId();
            Long userId = roleDTO.getUserId();
            LambdaQueryWrapper<SysUserRole> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(SysUserRole::getRoleId, roleId);
            wrapper.eq(SysUserRole::getUserId, userId);
            sysUserRoleService.remove(wrapper);
        }
    }

    @Override
    public Page<SysUser> getNoUsersByRole(LevelRoleDTO levelRoleDTO) {
        return sysUserMapper.selectNotByRoleId(PageWrapper.wrapper(levelRoleDTO), levelRoleDTO);
    }

    @Override
    public Page<SysUser> getUsersByRole(LevelRoleDTO levelRoleDTO) {
        return sysUserMapper.selectByRoleId(PageWrapper.wrapper(levelRoleDTO), levelRoleDTO);
    }

    @Override
    public Page<SysRole> getRolePage(RoleQueryDTO roleQueryDTO) {
        return baseMapper.selectPage(PageWrapper.wrapper(roleQueryDTO), Wrappers.lambdaQuery());
    }

    @Override
    public List<SysRole> getAllRoles() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }
}
