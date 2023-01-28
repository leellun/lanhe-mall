package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.service.SysMenuService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import com.newland.lanhe.user.model.dto.MenuQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Api(tags = "系统：菜单管理")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse buildMenu() {
        return RestResponse.ok(sysMenuService.getUserMenus());
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/lazy")
    @PreAuthorize("hasAuthority('menu:select','role:select')")
    public RestResponse<List<SysMenu>> listLazy(@RequestParam Long pid,
                                                @RequestParam Long roleId) {
        return RestResponse.ok(sysMenuService.getLazyList(pid,roleId));
    }

    @ApiOperation("根据菜单ID返回所有子节点ID，包含自身ID")
    @GetMapping(value = "/child/{id}")
    @PreAuthorize("hasAuthority('menu:select','Role:select')")
    public RestResponse<Set<Long>> child(@PathVariable Long id) {
        return RestResponse.ok(sysMenuService.getChildIds(id));
    }

    @ApiOperation("查询菜单")
    @GetMapping
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse query(@RequestBody MenuQueryDTO menuQueryDTO) throws Exception {
        return RestResponse.ok(sysMenuService.getMenus(menuQueryDTO));
    }

    @ApiOperation("查询菜单分页")
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse page(@RequestBody MenuQueryDTO menuQueryDTO) {
        return RestResponse.ok(sysMenuService.getMenuPage(menuQueryDTO));
    }

    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse getSuperior(@RequestBody List<Long> ids) {
        return RestResponse.ok(sysMenuService.getSuperior(ids));
    }

    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("hasAuthority('menu:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysMenu sysMenu) {
        sysMenuService.addMenu(sysMenu);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAuthority('menu:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysMenu sysMenu) {
        sysMenuService.updateMenu(sysMenu);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("hasAuthority('menu:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysMenuService.deleteMenu(ids);
        return RestResponse.success("删除成功");
    }
}

