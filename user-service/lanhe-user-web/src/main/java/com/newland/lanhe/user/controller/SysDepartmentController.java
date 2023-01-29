package com.newland.lanhe.user.controller;


import com.newland.lanhe.user.entity.SysDepartment;
import com.newland.lanhe.user.service.SysDepartmentService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.IntOptions;
import com.newland.lanhe.validator.Update;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.model.dto.DeptQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Api(tags = "系统：部门管理")
@RestController
@RequestMapping("/dept")
public class SysDepartmentController {
    @Autowired
    private SysDepartmentService sysDepartmentService;

    @ApiOperation("查询部门列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "deptQueryDTO", value = "查询部门", required = true,
            dataType = "DeptQueryDTO", paramType = "body")})
    @PostMapping("/list")
    //@PreAuthorize("hasAnyAuthority('user:select','dept:select')")
    public RestResponse query(@RequestBody DeptQueryDTO deptQueryDTO) {
        return RestResponse.ok(sysDepartmentService.getDepartments(deptQueryDTO));
    }
    @ApiOperation("查询部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "查询部门", required = true,
            dataType = "long", paramType = "path")})
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('dept:select')")
    public RestResponse get(@PathVariable Long id) {
        return RestResponse.ok(sysDepartmentService.getDepartment(id));
    }

    @ApiOperation("查询部门:获取同级与上级数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "获取同级与上级数据", required = true,
            dataType = "list", paramType = "body")})
    @GetMapping("/search")
    //@PreAuthorize("hasAnyAuthority('user:select','dept:select')")
    public RestResponse getDepartSearch(@RequestParam("name") String name) {
        return RestResponse.ok(sysDepartmentService.getDepartSearch(name));
    }

    @ApiOperation("查询部门:获取子部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "pid", value = "上级数据id", required = true,
            dataType = "long", paramType = "path")})
    @GetMapping("/sub/{pid}")
    //@PreAuthorize("hasAnyAuthority('user:select','dept:select')")
    public RestResponse<List<SysDepartment>> getSubDepts(@PathVariable("pid") Long pid) {
        return RestResponse.ok(sysDepartmentService.getSubDepts(pid));
    }

    @ApiOperation("新增部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "department", value = "新增部门", required = true,
            dataType = "SysDepartment", paramType = "body")})
    @PostMapping
    //@PreAuthorize("hasAuthority('dept:add')")
    public RestResponse add(@Validated(Insert.class) @RequestBody SysDepartment department) {
        sysDepartmentService.addDepartment(department);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "department", value = "修改部门", required = true,
            dataType = "SysDepartment", paramType = "body")})
    @PutMapping
    //@PreAuthorize("hasAuthority('dept:update')")
    public RestResponse update(@Validated(Update.class) @RequestBody SysDepartment department) {
        sysDepartmentService.updateDepartment(department);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("修改部门状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "部门id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "enable", value = "状态", required = true, dataType = "int", paramType = "param")})
    @PutMapping("/enable/{id}")
    //@PreAuthorize("hasAuthority('dept:update')")
    public RestResponse enable(@PathVariable("id") Long id, @RequestParam("enable") @Validated @IntOptions(options = {0, 1}, message = "状态不正确") Integer enable) {
        sysDepartmentService.enableDepartment(id, enable);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("修改部门排序")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id", required = true,
            dataType = "long", paramType = "path"), @ApiImplicitParam(name = "deptSort", value = "排序", required = true, dataType = "int", paramType = "param")})
    @PutMapping("/sort/{id}")
    //@PreAuthorize("hasAuthority('dept:update')")
    public RestResponse updateDeptSort(@PathVariable("id") Long id, @RequestParam("deptSort") @Validated @Min(value = 1, message = "不能小于1") @Max(value = 1000, message = "不能大于1000") Integer deptSort) {
        sysDepartmentService.updateDeptSort(id, deptSort);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("删除部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "删除部门id", required = true,
            dataType = "set", paramType = "body")})
    @DeleteMapping
    //@PreAuthorize("hasAuthority('dept:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysDepartmentService.deleteDepartment(ids);
        return RestResponse.success("删除成功");
    }
}

