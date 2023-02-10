package com.newland.lanhe.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.order.entity.OrderReturnApply;
import com.newland.lanhe.order.model.dto.ReturnApplyQueryDto;
import com.newland.lanhe.order.model.dto.UpdateStatusDto;
import com.newland.lanhe.order.model.vo.OrderReturnApplyResultVo;
import com.newland.lanhe.order.service.OrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "订单退货申请管理", description = "订单退货申请管理")
@RestController
@RequestMapping("/orderReturnApply")
public class OrderReturnApplyController {
    @Autowired
    private OrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @PostMapping(value = "/list")
    @ResponseBody
    public RestResponse<Page<OrderReturnApply>> list(@RequestBody  ReturnApplyQueryDto returnApplyQueryDto) {
        Page<OrderReturnApply> page = returnApplyService.list(returnApplyQueryDto);
        return RestResponse.success(page);
    }

    @ApiOperation("批量删除申请")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@RequestParam("ids") List<Long> ids) {
        returnApplyService.delete(ids);
        return RestResponse.success("删除成功");
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse getItem(@PathVariable Long id) {
        OrderReturnApplyResultVo result = returnApplyService.getItem(id);
        return RestResponse.success(result);
    }

    @ApiOperation("修改申请状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDto updateStatusDto) {
        returnApplyService.updateStatus(id, updateStatusDto);
        return RestResponse.success("修改成功");
    }
}

