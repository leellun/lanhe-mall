package com.newland.lanhe.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.entity.SysUser;
import com.newland.lanhe.user.model.dto.LevelRoleDTO;
import com.newland.lanhe.user.model.dto.RoleQueryDTO;
import com.newland.lanhe.user.model.dto.UserRoleDTO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 查询所有角色
     *
     * @return
     */
    List<SysRole> getAllRoles();

    /**
     * 获取单个角色
     *
     * @param id 角色id
     * @return
     */
    SysRole getRole(Long id);

    /**
     * 添加角色
     *
     * @param sysRole
     */
    void addRole(SysRole sysRole);

    /**
     * 更新角色
     *
     * @param sysRole
     */
    void updateRole(SysRole sysRole);

    /**
     * 删除角色
     *
     * @param ids
     */
    void deleteRoles(Set<Long> ids);

    /**
     * 添加用户角色绑定
     *
     * @param list
     */
    void addUserBinds(List<UserRoleDTO> list);

    /**
     * 删除用户角色绑定
     *
     * @param list
     */
    void deleteUsers(List<UserRoleDTO> list);

    /**
     * 获取非指定角色的下属用户
     *
     * @param levelRoleDTO
     * @return
     */
    Page<SysUser> getNoUsersByRole(LevelRoleDTO levelRoleDTO);

    /**
     * 获取指定角色的下属用户
     *
     * @param levelRoleDTO
     * @return
     */
    Page<SysUser> getUsersByRole(LevelRoleDTO levelRoleDTO);


    /**
     * 分页查询角色
     *
     * @param roleQueryDTO
     * @return
     */
    Page<SysRole> getRolePage(RoleQueryDTO roleQueryDTO);

}
