package com.newland.lanhe.system.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.system.entity.SysRole;
import com.newland.lanhe.system.service.SysRoleService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.IntOptions;
import com.newland.lanhe.validator.Update;
import com.newland.lanhe.system.model.dto.RoleQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse query(@PathVariable Long id) {
        return RestResponse.ok(sysRoleService.getRole(id));
    }

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyAuthority('role:select','user:select')")
    public RestResponse all() {
        return RestResponse.ok(sysRoleService.getAllRoles());
    }

    @ApiOperation("查询角色")
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse list(@RequestBody RoleQueryDTO roleQueryDTO) {
        return RestResponse.ok(sysRoleService.getRolePage(roleQueryDTO));
    }

    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("hasAuthority('role:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysRole sysRole) {
        sysRoleService.addRole(sysRole);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("hasAuthority('role:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysRole sysRole) {
        sysRoleService.updateRole(sysRole);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("修改状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "enable", value = "状态", required = true, dataType = "int", paramType = "param")})
    @PutMapping("/enable/{id}")
    @PreAuthorize("hasAuthority('role:update')")
    public RestResponse enable(@PathVariable("id") Long id, @RequestParam("enable") @Validated @IntOptions(options = {0, 1}, message = "状态不正确") Integer enable) {
        sysRoleService.enableRole(id, enable);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("删除角色")
    @DeleteMapping
    @PreAuthorize("hasAuthority('role:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysRoleService.deleteRoles(ids);
        return RestResponse.success("删除成功");
    }

    @ApiOperation("添加权限")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "permissions", value = "权限", required = true, dataType = "Set", paramType = "body")})
    @PostMapping("/permission/{id}")
    @PreAuthorize("hasAuthority('role:add')")
    public RestResponse addMenuPermission(@PathVariable("id") Long id, @RequestBody Set<Long> permissions) {
        sysRoleService.addMenuPermission(id, permissions);
        return RestResponse.success("设置成功");
    }

    @ApiOperation("获取权限")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id", required = true,
            dataType = "long", paramType = "path")})
    @GetMapping("/permission/{id}")
    @PreAuthorize("hasAuthority('role:select')")
    public RestResponse getMenuPermission(@PathVariable("id") Long id) {
        List<Long> list = sysRoleService.getMenuPermission(id);
        return RestResponse.ok(list.stream().map(String::valueOf).collect(Collectors.toList()));
    }
}

