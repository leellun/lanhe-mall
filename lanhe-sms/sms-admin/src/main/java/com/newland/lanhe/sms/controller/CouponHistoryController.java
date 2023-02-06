package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.CouponHistory;
import com.newland.lanhe.sms.service.CouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 优惠券使用、领取历史表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "优惠券领取记录管理", description = "优惠券领取记录管理")
@RestController
@RequestMapping("/couponHistory")
public class CouponHistoryController {
    @Autowired
    private CouponHistoryService historyService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<CouponHistory>> list(@RequestParam(value = "couponId", required = false) Long couponId,
                                                  @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                  @RequestParam(value = "orderSn", required = false) String orderSn,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<CouponHistory> page = historyService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return RestResponse.success(page);
    }
}

