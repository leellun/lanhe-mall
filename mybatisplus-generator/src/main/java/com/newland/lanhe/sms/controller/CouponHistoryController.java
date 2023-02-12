package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.sms.entity.CouponHistory;
import com.newland.lanhe.sms.service.CouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@RestController
@Api(tags = "优惠券领取记录管理", description = "优惠券领取记录管理")
@RequestMapping("/couponHistory")
public class CouponHistoryController {
}

