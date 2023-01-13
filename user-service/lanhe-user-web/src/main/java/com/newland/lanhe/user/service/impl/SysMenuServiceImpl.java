package com.newland.lanhe.user.service.impl;

import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.mapper.SysMenuMapper;
import com.newland.lanhe.user.mapper.SysRoleMapper;
import com.newland.lanhe.user.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> getPermissions(Long userId) {
        List<SysRole> roles = sysRoleMapper.getRoleNameWithIdByUserId(userId);
        List<String> permissions = baseMapper.getPermissions(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
        permissions.addAll(roles.stream().map(item -> "ROLE_" + item.getName()).collect(Collectors.toList()));
        return permissions;
    }
}
