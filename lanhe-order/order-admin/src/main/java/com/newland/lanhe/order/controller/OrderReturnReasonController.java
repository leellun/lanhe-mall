package com.newland.lanhe.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.order.entity.OrderReturnReason;
import com.newland.lanhe.order.service.OrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "退货原因管理", description = "退货原因管理")
@RestController
@RequestMapping("/returnReason")
public class OrderReturnReasonController {
    @Autowired
    private OrderReturnReasonService orderReturnReasonService;

    @ApiOperation("添加退货原因")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody OrderReturnReason returnReason) {
        orderReturnReasonService.create(returnReason);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改退货原因")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody OrderReturnReason returnReason) {
        orderReturnReasonService.update(id, returnReason);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("批量删除退货原因")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@RequestParam("ids") List<Long> ids) {
        orderReturnReasonService.delete(ids);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("分页查询全部退货原因")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<OrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<OrderReturnReason> page = orderReturnReasonService.list(pageSize, pageNo);
        return RestResponse.success(page);
    }

    @ApiOperation("获取单个退货原因详情信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<OrderReturnReason> getItem(@PathVariable Long id) {
        OrderReturnReason reason = orderReturnReasonService.getItem(id);
        return RestResponse.success(reason);
    }

    @ApiOperation("修改退货原因启用状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam("ids") List<Long> ids) {
        orderReturnReasonService.updateStatus(ids, status);
        return RestResponse.success("操作成功");
    }
}

