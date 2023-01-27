package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.service.SysRoleService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import com.newland.lanhe.user.model.dto.LevelRoleDTO;
import com.newland.lanhe.user.model.dto.RoleQueryDTO;
import com.newland.lanhe.user.model.dto.UserRoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Api(tags = "系统：角色管理")
@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @ApiOperation("获取单个role")
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasAuthority('role:select')")
    public RestResponse query(@PathVariable Long id) {
        return RestResponse.success(sysRoleService.getRole(id));
    }

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    //@PreAuthorize("hasAnyAuthority('role:select','user:select')")
    public RestResponse list() {
        return RestResponse.success(sysRoleService.getAllRoles());
    }

    @ApiOperation("查询角色")
    @GetMapping
    //@PreAuthorize("hasAuthority('role:select')")
    public RestResponse list(@RequestBody RoleQueryDTO roleQueryDTO) {
        return RestResponse.success(sysRoleService.getRolePage(roleQueryDTO));
    }

    @ApiOperation("新增角色")
    @PostMapping
    //@PreAuthorize("hasAuthority('role:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysRole sysRole) {
        sysRoleService.addRole(sysRole);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改角色")
    @PutMapping
    //@PreAuthorize("hasAuthority('role:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysRole sysRole) {
        sysRoleService.updateRole(sysRole);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("删除角色")
    @DeleteMapping
    //@PreAuthorize("hasAuthority('role:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysRoleService.deleteRoles(ids);
        return RestResponse.success("删除成功");
    }

    @ApiOperation("查询下属用户")
    @GetMapping("/users")
    //@PreAuthorize("hasAuthority('role:select')")
    public RestResponse listUsersByRole(@RequestBody LevelRoleDTO levelRoleDTO) {
        return RestResponse.success(sysRoleService.getUsersByRole(levelRoleDTO));
    }

    @ApiOperation("查询非下属用户")
    @GetMapping("/not/users")
    //@PreAuthorize("hasAuthority('role:select')")
    public RestResponse listNotByRole(@RequestBody LevelRoleDTO levelRoleDTO) {
        return RestResponse.success(sysRoleService.getNoUsersByRole(levelRoleDTO));
    }

    @ApiOperation("删除下属用户")
    @DeleteMapping("/users")
    //@PreAuthorize("hasAuthority('role:delete')")
    public RestResponse deleteUsers(@RequestBody List<UserRoleDTO> list) {
        sysRoleService.deleteUsers(list);
        return RestResponse.success("删除成功");
    }

    @ApiOperation("添加下属用户")
    @PostMapping("/users")
    //@PreAuthorize("hasAuthority('role:delete')")
    public RestResponse addUsers(@RequestBody List<UserRoleDTO> list) {
        sysRoleService.addUserBinds(list);
        return RestResponse.success("添加成功");
    }
}

