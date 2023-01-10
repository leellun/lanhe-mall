package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysDepartment;
import com.newland.lanhe.user.model.dto.DeptQueryDTO;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation("查询部门")
    @GetMapping
    @PreAuthorize("hasAuthority('user:select','dept:select')")
    public RestResponse query(@RequestBody DeptQueryDTO deptQueryDTO) throws Exception {
        return null;
    }

    @ApiOperation("查询部门:获取同级与上级数据")
    @PostMapping("/superior")
    @PreAuthorize("hasAuthority('user:select','dept:select')")
    public RestResponse getSuperior(@RequestBody List<Long> ids) {
        return null;
    }

    @ApiOperation("新增部门")
    @PostMapping
    @PreAuthorize("hasAuthority('dept:add')")
    public RestResponse add(@Validated(Insert.class ) @RequestBody SysDepartment department) {
        return null;
    }

    @ApiOperation("修改部门")
    @PutMapping
    @PreAuthorize("hasAuthority('dept:update')")
    public RestResponse update(@Validated(Update.class)  @RequestBody SysDepartment department) {
        return null;
    }

    @ApiOperation("删除部门")
    @DeleteMapping
    @PreAuthorize("hasAuthority('dept:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        return null;
    }
}

