package com.newland.lanhe.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.enumeration.BasicEnum;
import com.newland.lanhe.exception.BusinessException;
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
import com.newland.lanhe.user.model.vo.MenuVo;
import com.newland.lanhe.user.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.lanhe.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
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
    public void addMenu(SysMenu sysMenu) {
        if (sysMenu.getPid() != null) {
            SysMenu parentMenu = baseMapper.selectById(sysMenu.getPid());
            AssertUtil.notNull(parentMenu, UserServiceErrorEnum.MENU_PARENT_NOT_EXIST);
        }
        sysMenu.setSubCount(0);
        baseMapper.insert(sysMenu);
        if (sysMenu.getPid() != null) {
            this.updateSubCount(sysMenu.getPid());
        }
    }

    @Override
    public void updateMenu(SysMenu sysMenu) {
        SysMenu dbDepartment = baseMapper.selectById(sysMenu.getId());
        AssertUtil.notNull(dbDepartment, UserServiceErrorEnum.MENU_NOT_EXIST);
        baseMapper.update(new SysMenu(), Wrappers.<SysMenu>lambdaUpdate()
                .set(SysMenu::getName, sysMenu.getName())
                .set(SysMenu::getPid, sysMenu.getPid())
                .set(SysMenu::getMenuSort, sysMenu.getMenuSort())
                .set(SysMenu::getType, sysMenu.getType())
                .set(SysMenu::getTitle, sysMenu.getTitle())
                .set(SysMenu::getComponent, sysMenu.getComponent())
                .set(SysMenu::getIcon, sysMenu.getIcon())
                .set(SysMenu::getPath, sysMenu.getPath())
                .set(SysMenu::getTarget, sysMenu.getTarget())
                .set(SysMenu::getKeepAlive, sysMenu.getKeepAlive())
                .set(SysMenu::getHidden, sysMenu.getHidden())
                .set(SysMenu::getPermission, sysMenu.getPermission())
                .eq(SysMenu::getId, sysMenu.getId())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = BusinessException.class)
    public void deleteMenu(Set<Long> ids) {
        List<Long> pids = new ArrayList<>();
        ids.forEach(id -> {
            SysMenu sysMenu = baseMapper.selectOne(Wrappers.<SysMenu>lambdaQuery().select(SysMenu::getPid).eq(SysMenu::getId, id));
            if (sysMenu != null) {
                pids.add(sysMenu.getPid());
            }
        });
        int count = baseMapper.deleteBatchIds(getSuperior(ids));
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.MENU_DELETE_FAIL);
        pids.forEach(id -> {
            this.updateSubCount(id);
        });
    }

    private List<Long> getSuperior(Set<Long> ids) {
        List<Long> list = new ArrayList();
        List<SysMenu> menus = baseMapper.selectBatchIds(ids);
        while (menus.size() > 0) {
            List<SysMenu> newMenus = new ArrayList<>();
            List<Long> tempIds = new ArrayList<>();
            for (SysMenu menu : menus) {
                tempIds.add(menu.getId());
            }
            list.addAll(tempIds);
            menus = baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().select(SysMenu::getId).in(SysMenu::getPid, tempIds));
        }
        return list;
    }

    @Override
    public List<String> getPermissions(Long userId) {
        List<SysRole> roles = sysRoleMapper.getRoleNameWithIdByUserId(userId);
        List<String> permissions = baseMapper.getPermissions(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
        permissions.addAll(roles.stream().map(item -> "ROLE_" + item.getName()).collect(Collectors.toList()));
        return permissions;
    }

    @Override
    public List<SysMenu> getSubMenus(Long pid) {
        if (pid == null || pid == 0) {
            return baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().isNull(SysMenu::getPid).orderByAsc(SysMenu::getMenuSort));
        } else {
            return baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, pid).orderByAsc(SysMenu::getMenuSort));
        }
    }

    @Override
    public Page<SysMenu> getMenuPage(PageEntity pageEntity) {
        pageEntity.setOrder("menuSort");
        Page<SysMenu> page = PageWrapper.wrapper(pageEntity);
        return baseMapper.selectPage(page, Wrappers.<SysMenu>lambdaQuery().isNull(SysMenu::getPid));
    }

    @Override
    public void updateMenuSort(Long id, Integer menuSort) {
        SysMenu dbMenu = baseMapper.selectById(id);
        AssertUtil.notNull(dbMenu, UserServiceErrorEnum.MENU_NOT_EXIST);
        baseMapper.update(new SysMenu(), Wrappers.<SysMenu>lambdaUpdate()
                .set(SysMenu::getMenuSort, menuSort)
                .eq(SysMenu::getId, id)
        );
    }

    @Override
    public void enableMenu(Long id, Integer enable) {
        SysMenu dbMenu = baseMapper.selectById(id);
        AssertUtil.notNull(dbMenu, UserServiceErrorEnum.MENU_NOT_EXIST);
        baseMapper.update(new SysMenu(), Wrappers.<SysMenu>lambdaUpdate()
                .set(SysMenu::getEnabled, enable)
                .eq(SysMenu::getId, id)
        );
    }

    /**
     * 更新子数目
     *
     * @param id id
     */
    private void updateSubCount(Long id) {
        int count = baseMapper.selectCount(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, id));
        baseMapper.update(null, Wrappers.<SysMenu>lambdaUpdate().set(SysMenu::getSubCount, count).eq(SysMenu::getId, id));
    }

    @Override
    public MenuVo getMenu(Long id) {
        SysMenu dbMenu = baseMapper.selectById(id);
        MenuVo menuVo = new MenuVo();
        BeanUtils.copyProperties(dbMenu, menuVo);
        if (dbMenu.getPid() != null) {
            SysMenu parentMenu = baseMapper.selectById(dbMenu.getPid());
            if (parentMenu != null) {
                menuVo.setParentName(parentMenu.getTitle());
            }
        }
        return menuVo;
    }

    /**
     * 检查菜单添加修改菜单数据
     *
     * @param sysMenu
     */
    private void checkMenu(SysMenu sysMenu) {
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
    public List<SysMenu> getMenus(MenuQueryDTO menuQueryDTO) {
        List<SysMenu> menus;
        if (menuQueryDTO.getPid() != null && !menuQueryDTO.getPid().equals(0L)) {
            menus = this.baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, menuQueryDTO.getPid()));
        } else {
            menus = this.baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().isNull(SysMenu::getPid));
        }
        return menus;
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

}
