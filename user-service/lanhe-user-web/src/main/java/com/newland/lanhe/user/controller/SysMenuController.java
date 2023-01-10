package com.newland.lanhe.user.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.model.dto.MenuQueryDTO;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse buildMenus() {
        return RestResponse.success();
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/lazy/{pid}")
    @PreAuthorize("hasAuthority('menu:select','roles:select')")
    public RestResponse listLazy(@PathVariable("pid") Long pid) {
        return RestResponse.success();
    }

    @ApiOperation("根据菜单ID返回所有子节点ID，包含自身ID")
    @GetMapping(value = "/child/{id}")
    @PreAuthorize("hasAuthority('menu:select','roles:select')")
    public RestResponse child(@PathVariable Long id) {
        return RestResponse.success();
    }

    @ApiOperation("查询菜单")
    @GetMapping
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse query(@RequestBody MenuQueryDTO menuQueryDTO) throws Exception {
        return RestResponse.success();
    }

    @ApiOperation("查询菜单分页")
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse page(@RequestBody MenuQueryDTO menuQueryDTO) {
        return RestResponse.success();
    }

    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    @PreAuthorize("hasAuthority('menu:select')")
    public RestResponse getSuperior(@RequestBody List<Long> ids) {
        return RestResponse.success();
    }

    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("hasAuthority('menu:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysMenu sysMenu) {
        return RestResponse.success();
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAuthority('menu:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysMenu sysMenu) {
        return RestResponse.success();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("hasAuthority('menu:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        return RestResponse.success();
    }
}

