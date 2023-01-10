package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysRole;
import com.newland.lanhe.user.model.dto.LevelRoleDTO;
import com.newland.lanhe.user.model.dto.RoleQueryDTO;
import com.newland.lanhe.user.model.dto.UserRoleDTO;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation("获取单个role")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse query(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('role:select','user:select')")
    public RestResponse list() {
        return null;
    }

    @ApiOperation("查询角色")
    @GetMapping
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse get(@RequestBody RoleQueryDTO roleQueryDTO) {
        return null;
    }

    @ApiOperation("获取用户级别")
    @GetMapping(value = "/level")
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse getLevel() {
        return null;
    }

    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("hasAuthority('role:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysRole sysRole) {
        return RestResponse.success();
    }

    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("hasAuthority('role:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysRole sysRole) {
        return RestResponse.success();
    }

    @ApiOperation("修改角色菜单")
    @PutMapping(value = "/menu")
    @PreAuthorize("hasAuthority('role:update')")
    public RestResponse updateMenu(@RequestBody @Validated(Update.class) SysRole sysRole) {
        return RestResponse.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping
    @PreAuthorize("hasAuthority('role:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        return RestResponse.success();
    }

    @ApiOperation("查询下属用户")
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse listByRole(@RequestBody LevelRoleDTO levelRoleDTO) {
        return RestResponse.success();
    }

    @ApiOperation("查询非下属用户")
    @GetMapping("/not/users")
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse listNotByRole(@RequestBody LevelRoleDTO levelRoleDTO) {
        return RestResponse.success();
    }

    @ApiOperation("删除下属用户")
    @DeleteMapping("/users")
    @PreAuthorize("hasAuthority('role:del')")
    public RestResponse deleteUsers(@RequestBody List<UserRoleDTO> list) {
        return RestResponse.success();
    }

    @ApiOperation("添加下属用户")
    @PostMapping("/users")
    @PreAuthorize("hasAuthority('role:del')")
    public RestResponse addUsers(@RequestBody List<UserRoleDTO> list) {
        return RestResponse.success();
    }
}

