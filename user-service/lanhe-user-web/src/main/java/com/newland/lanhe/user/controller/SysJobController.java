package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import com.newland.lanhe.user.entity.SysJob;
import com.newland.lanhe.user.model.dto.JobQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Api(tags = "系统：岗位管理")
@RestController
@RequestMapping("/job")
public class SysJobController {

    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("hasAuthority('job:select','user:select')")
    public RestResponse list(@RequestBody JobQueryDTO jobQueryDTO) {
        return RestResponse.success();
    }

    @ApiOperation("新增岗位")
    @PostMapping
    @PreAuthorize("hasAuthority('job:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysJob sysJob) {
        return RestResponse.success();
    }

    @ApiOperation("修改岗位")
    @PutMapping
    @PreAuthorize("hasAuthority('job:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysJob sysJob) {
        return RestResponse.success();
    }

    @ApiOperation("删除岗位")
    @DeleteMapping
    @PreAuthorize("hasAuthority('job:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        return RestResponse.success();
    }
}

