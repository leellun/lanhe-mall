package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysMenu;
import com.newland.lanhe.user.model.vo.MenuVo;
import com.newland.lanhe.user.service.SysMenuService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.IntOptions;
import com.newland.lanhe.validator.Update;
import com.newland.mybatis.page.PageEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @ApiOperation("查询菜单分页")
    @GetMapping
    //@PreAuthorize("hasAuthority('menu:select')")
    public RestResponse page(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {
        return RestResponse.ok(sysMenuService.getMenuPage(PageEntity.page(pageNo,pageSize)));
    }
    @ApiOperation("返回子菜单")
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasAuthority('menu:select')")
    public RestResponse<MenuVo> getMenu(@PathVariable Long id) {
        return RestResponse.ok(sysMenuService.getMenu(id));
    }
    @ApiOperation("返回子菜单")
    @GetMapping(value = "/sub/{id}")
    //@PreAuthorize("hasAuthority('menu:select')")
    public RestResponse<Set<Long>> getSubMenus(@PathVariable Long id) {
        return RestResponse.ok(sysMenuService.getSubMenus(id));
    }
    @ApiOperation("新增菜单")
    @PostMapping
    //@PreAuthorize("hasAuthority('menu:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysMenu sysMenu) {
        sysMenuService.addMenu(sysMenu);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改菜单")
    @PutMapping
    //@PreAuthorize("hasAuthority('menu:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysMenu sysMenu) {
        sysMenuService.updateMenu(sysMenu);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping
    // @PreAuthorize("hasAuthority('menu:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysMenuService.deleteMenu(ids);
        return RestResponse.success("删除成功");
    }
    @ApiOperation("修改部门排序")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "菜单id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "menuSort", value = "排序", required = true, dataType = "int", paramType = "param")})
    @PutMapping("/sort/{id}")
    //@PreAuthorize("hasAuthority('menu:update')")
    public RestResponse updateDeptSort(@PathVariable("id") Long id, @RequestParam("menuSort") @Validated @Min(value = 1, message = "不能小于1") @Max(value = 1000, message = "不能大于1000") Integer menuSort) {
        sysMenuService.updateMenuSort(id, menuSort);
        return RestResponse.success("更新成功");
    }
    @ApiOperation("修改菜单状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "菜单id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "enable", value = "状态", required = true, dataType = "int", paramType = "param")})
    @PutMapping("/enable/{id}")
    //@PreAuthorize("hasAuthority('dept:update')")
    public RestResponse enable(@PathVariable("id") Long id, @RequestParam("enable") @Validated @IntOptions(options = {0, 1}, message = "状态不正确") Integer enable) {
        sysMenuService.enableMenu(id, enable);
        return RestResponse.success("更新成功");
    }


//    @GetMapping(value = "/build")
//    @ApiOperation("获取前端所需菜单")
//    @PreAuthorize("hasAuthority('menu:select')")
//    public RestResponse buildMenu() {
//        return RestResponse.ok(sysMenuService.getUserMenus());
//    }
//
//    @ApiOperation("返回全部的菜单")
//    @GetMapping(value = "/lazy")
//    @PreAuthorize("hasAuthority('menu:select','role:select')")
//    public RestResponse<List<SysMenu>> listLazy(@RequestParam Long pid,
//                                                @RequestParam Long roleId) {
//        return RestResponse.ok(sysMenuService.getLazyList(pid,roleId));
//    }
//    @ApiOperation("查询菜单")
//    @GetMapping
//    @PreAuthorize("hasAuthority('menu:select')")
//    public RestResponse query(@RequestBody MenuQueryDTO menuQueryDTO) throws Exception {
//        return RestResponse.ok(sysMenuService.getMenus(menuQueryDTO));
//    }
//    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
//    @PostMapping("/superior")
//    @PreAuthorize("hasAuthority('menu:select')")
//    public RestResponse getSuperior(@RequestBody List<Long> ids) {
//        return RestResponse.ok(sysMenuService.getSuperior(ids));
//    }


}

