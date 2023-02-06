package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.HomeAdvertise;
import com.newland.lanhe.sms.service.HomeAdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "首页轮播广告管理", description = "首页轮播广告管理")
@RestController
@RequestMapping("/homeAdvertise")
public class HomeAdvertiseController {
    @Autowired
    private HomeAdvertiseService advertiseService;

    @ApiOperation("添加广告")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody HomeAdvertise advertise) {
        advertiseService.create(advertise);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("删除广告")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@RequestParam("ids") List<Long> ids) {
        advertiseService.delete(ids);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateStatus(@PathVariable Long id, Integer status) {
        advertiseService.updateStatus(id, status);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("获取广告详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<HomeAdvertise> getItem(@PathVariable Long id) {
        HomeAdvertise advertise = advertiseService.getItem(id);
        return RestResponse.success(advertise);
    }

    @ApiOperation("修改广告")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody HomeAdvertise advertise) {
        advertiseService.update(id, advertise);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("分页查询广告")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<HomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "type", required = false) Integer type,
                                                  @RequestParam(value = "endTime", required = false) String endTime,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<HomeAdvertise> page = advertiseService.list(name, type, endTime, pageSize, pageNum);
        return RestResponse.success(page);
    }
}

