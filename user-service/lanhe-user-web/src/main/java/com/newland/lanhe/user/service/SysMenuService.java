package com.newland.lanhe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.user.entity.SysMenu;

import java.util.List;

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
}
