package com.newland.lanhe.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.security.utils.SecurityUtil;
import com.newland.lanhe.user.entity.SysDepartment;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.enums.MenuTypeEnum;
import com.newland.lanhe.user.enums.UserServiceErrorEnum;
import com.newland.lanhe.user.mapper.SysMenuMapper;
import com.newland.lanhe.user.mapper.SysRoleMapper;
import com.newland.lanhe.user.model.dto.MenuQueryDTO;
import com.newland.lanhe.user.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @Override
    public List<SysMenu> getUserMenus() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        List<SysRole> roles = sysRoleMapper.getRoleNameWithIdByUserId(loginUser.getUserId());
        List<SysMenu> menus = baseMapper.getMenuList(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
        return menus;
    }

    @Override
    public List<SysMenu> getLazyList(Long pid, Long roleId) {
        return baseMapper.getLazyMenus(pid, roleId);
    }

    @Override
    public Set<Long> getChildIds(Long id) {
        SysMenu sysMenu = baseMapper.selectById(id);
        List<SysMenu> menus = new ArrayList<>();
        menus.add(sysMenu);
        menus.addAll(getMenus(sysMenu));
        return menus.stream().map(SysMenu::getId).collect(Collectors.toSet());
    }

    @Override
    public List<SysMenu> getMenus(MenuQueryDTO menuQueryDTO) {
        List<SysMenu> menus;
        if (menuQueryDTO.getPid() != null && !menuQueryDTO.getPid().equals(0L)) {
            menus = this.baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, menuQueryDTO.getPid()));
        } else {
            menus = this.baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().isNull(SysMenu::getPid));
        }
        return menus;
    }

    /**
     * 获取子菜单
     *
     * @param sysMenu
     * @return
     */
    private List<SysMenu> getMenus(SysMenu sysMenu) {
        List<SysMenu> menus = new ArrayList<>();
        List<SysMenu> list = baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, sysMenu.getId()));
        menus.addAll(list);
        for (SysMenu menu : list) {
            menus.addAll(getMenus(menu));
        }
        return menus;
    }

    @Override
    public Page<SysMenu> getMenuPage(MenuQueryDTO menuQueryDTO) {
        return baseMapper.selectPage(PageWrapper.wrapper(menuQueryDTO), Wrappers.lambdaQuery());
    }

    @Override
    public List<SysMenu> getSuperior(List<Long> ids) {
        List<SysMenu> list = new ArrayList<>();
        List<Long> tempIds = ids;
        boolean pidNull = false;
        for (int i = 0; i < 2; i++) {
            List<SysMenu> pMenus = baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().select(SysMenu::getPid).select(SysMenu::getId).in(SysMenu::getId, tempIds));
            List<SysMenu> newDepts = new ArrayList<>();
            for (SysMenu department : pMenus) {
                if (department.getPid() == null) {
                    if (!pidNull) {
                        pidNull = true;
                        newDepts.addAll(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().isNull(SysMenu::getPid)));
                    }
                } else {
                    newDepts.addAll(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, department.getPid())));
                }
            }
            list.addAll(newDepts);
            tempIds = pMenus.stream().filter(item -> item.getPid() != null).map(SysMenu::getPid).collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public void addMenu(SysMenu sysMenu) {
        baseMapper.insert(sysMenu);
    }

    @Override
    public void updateMenu(SysMenu sysMenu) {
        SysMenu dbDepartment = baseMapper.selectById(sysMenu.getId());
        AssertUtil.notNull(dbDepartment, UserServiceErrorEnum.MENU_NOT_EXIST);
        sysMenu.setId(dbDepartment.getId());
        baseMapper.updateById(dbDepartment);
    }

    @Override
    public void deleteMenu(Set<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.MENU_DELETE_FAIL);
    }
}
