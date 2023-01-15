package com.newland.lanhe.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.model.dto.MenuQueryDTO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    List<String> getPermissions(Long userId);

    /**
     * 获取用户菜单
     * @return
     */
    List<SysMenu> getUserMenus();

    /**
     * 返回全部的菜单
     * @param pid
     * @return
     */
    List<SysMenu> getLazyList(Long pid,Long roleId);

    /**
     * 根据菜单ID返回所有子节点ID，包含自身ID
     * @param id
     * @return
     */
    Set<Long> getChildIds(Long id);

    /**
     * 获取菜单
     * @param menuQueryDTO
     * @return
     */
    List<SysMenu> getMenus(MenuQueryDTO menuQueryDTO);
    /**
     * 获取分页菜单
     * @param menuQueryDTO
     * @return
     */
    Page<SysMenu> getMenuPage(MenuQueryDTO menuQueryDTO);

    /**
     * 根据ID获取同级与上级数据
     * @param ids
     * @return
     */
    List<SysMenu> getSuperior(List<Long> ids);

    /**
     * 添加菜单
     * @param sysMenu
     */
    void addMenu(SysMenu sysMenu);

    /**
     * 修改菜单
     * @param sysMenu
     */
    void updateMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     * @param ids
     */
    void deleteMenu(Set<Long> ids);

}
