package com.newland.lanhe.order.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.order.entity.OrderSetting;
import com.newland.lanhe.order.service.OrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单设置表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "订单设置管理", description = "订单设置管理")
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {
    @Autowired
    private OrderSettingService orderSettingService;

    @ApiOperation("获取指定订单设置")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<OrderSetting> getItem(@PathVariable Long id) {
        OrderSetting orderSetting = orderSettingService.getItem(id);
        return RestResponse.success(orderSetting);
    }

    @ApiOperation("修改指定订单设置")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody OrderSetting orderSetting) {
        orderSettingService.update(id,orderSetting);
        return RestResponse.success("操作成功");
    }
}

