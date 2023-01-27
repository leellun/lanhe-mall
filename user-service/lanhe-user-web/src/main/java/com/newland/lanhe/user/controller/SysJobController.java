package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.user.entity.SysJob;
import com.newland.lanhe.user.service.SysJobService;
import com.newland.lanhe.validator.Insert;
import com.newland.lanhe.validator.Update;
import com.newland.lanhe.user.model.dto.JobQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysJobService sysJobService;
    @ApiOperation("返回全部的岗位")
    @GetMapping(value = "/all")
    //@PreAuthorize("hasAnyAuthority('job:select','user:select')")
    public RestResponse all() {
        return RestResponse.success(sysJobService.getAllJobs());
    }
    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('job:select','user:select')")
    public RestResponse list(@RequestBody JobQueryDTO jobQueryDTO) {
        return RestResponse.success(sysJobService.getJobs(jobQueryDTO));
    }

    @ApiOperation("新增岗位")
    @PostMapping
    @PreAuthorize("hasAuthority('job:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysJob sysJob) {
        sysJobService.addJob(sysJob);
        return RestResponse.success("添加岗位成功");
    }

    @ApiOperation("修改岗位")
    @PutMapping
    @PreAuthorize("hasAuthority('job:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysJob sysJob) {
        sysJobService.updateJob(sysJob);
        return RestResponse.success("更新岗位成功");
    }

    @ApiOperation("删除岗位")
    @DeleteMapping
    @PreAuthorize("hasAuthority('job:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysJobService.deleteJob(ids);
        return RestResponse.success("删除岗位成功");
    }
}

