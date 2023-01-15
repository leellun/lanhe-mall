package com.newland.lanhe.user.controller;


import com.newland.lanhe.user.entity.SysDepartment;
import com.newland.lanhe.user.service.SysDepartmentService;
import com.newland.lanhe.validator.Insert;
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
    @ApiOperation("查询部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "deptQueryDTO", value = "查询部门", required = true,
            dataType = "DeptQueryDTO",paramType = "body")})
    @GetMapping
    @PreAuthorize("hasAuthority('user:select','dept:select')")
    public RestResponse query(@RequestBody DeptQueryDTO deptQueryDTO) {
        return RestResponse.success(sysDepartmentService.getDepartments(deptQueryDTO));
    }

    @ApiOperation("查询部门:获取同级与上级数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "获取同级与上级数据", required = true,
            dataType = "list",paramType = "body")})
    @PostMapping("/superior")
    @PreAuthorize("hasAuthority('user:select','dept:select')")
    public RestResponse getSuperior(@RequestBody List<Long> ids) {
        return RestResponse.success(sysDepartmentService.getSuperior(ids));
    }

    @ApiOperation("新增部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "department", value = "新增部门", required = true,
            dataType = "SysDepartment",paramType = "body")})
    @PostMapping
    @PreAuthorize("hasAuthority('dept:add')")
    public RestResponse add(@Validated(Insert.class ) @RequestBody SysDepartment department) {
        sysDepartmentService.addDepartment(department);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "department", value = "修改部门", required = true,
            dataType = "SysDepartment",paramType = "body")})
    @PutMapping
    @PreAuthorize("hasAuthority('dept:update')")
    public RestResponse update(@Validated(Update.class)  @RequestBody SysDepartment department) {
        sysDepartmentService.updateDepartment(department);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("删除部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "删除部门id", required = true,
            dataType = "set",paramType = "body")})
    @DeleteMapping
    @PreAuthorize("hasAuthority('dept:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysDepartmentService.deleteDepartment(ids);
        return RestResponse.success("删除成功");
    }
}

