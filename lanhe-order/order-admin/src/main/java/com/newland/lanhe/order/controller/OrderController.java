package com.newland.lanhe.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.order.entity.Order;
import com.newland.lanhe.order.model.dto.MoneyInfoDto;
import com.newland.lanhe.order.model.dto.OrderDeliveryDto;
import com.newland.lanhe.order.model.dto.OrderQueryDto;
import com.newland.lanhe.order.model.dto.ReceiverInfoDto;
import com.newland.lanhe.order.model.vo.OrderDetailVo;
import com.newland.lanhe.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "订单管理", description = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<Order>> list(OrderQueryDto orderQueryDto) {
        Page<Order> orderList = orderService.list(orderQueryDto);
        return RestResponse.success(orderList);
    }

    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delivery(@RequestBody List<OrderDeliveryDto> deliveryDtos) {
        orderService.delivery(deliveryDtos);
        return RestResponse.success("發貨成功");
    }

    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        orderService.close(ids, note);
        return RestResponse.success("关闭成功");
    }

    @ApiOperation("批量删除订单")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public RestResponse delete(@RequestParam("ids") List<Long> ids) {
        orderService.delete(ids);
        return RestResponse.success("删除成功");
    }

    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<OrderDetailVo> detail(@PathVariable Long id) {
        OrderDetailVo orderDetailResult = orderService.detail(id);
        return RestResponse.success(orderDetailResult);
    }

    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateReceiverInfo(@RequestBody ReceiverInfoDto receiverInfoParam) {
        orderService.updateReceiverInfo(receiverInfoParam);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("修改订单费用信息")
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateReceiverInfo(@RequestBody MoneyInfoDto moneyInfoDto) {
        orderService.updateMoneyInfo(moneyInfoDto);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        orderService.updateNote(id, note, status);
        return RestResponse.success("备注成功");
    }
}

