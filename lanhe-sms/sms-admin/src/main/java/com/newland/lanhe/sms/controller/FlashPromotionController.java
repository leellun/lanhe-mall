package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.FlashPromotion;
import com.newland.lanhe.sms.service.FlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "限时购活动管理", description = "限时购活动管理")
@RestController
@RequestMapping("/flashPromotion")
public class FlashPromotionController {
    @Autowired
    private FlashPromotionService flashPromotionService;

    @ApiOperation("添加活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody FlashPromotion flashPromotion) {
        flashPromotionService.create(flashPromotion);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("编辑活动信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody FlashPromotion flashPromotion) {
        flashPromotionService.update(id, flashPromotion);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("删除活动信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@PathVariable Long id) {
        flashPromotionService.delete(id);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, Integer status) {
        flashPromotionService.updateStatus(id, status);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("获取活动详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<FlashPromotion> getItem(@PathVariable Long id) {
        FlashPromotion flashPromotion = flashPromotionService.getItem(id);
        return RestResponse.success(flashPromotion);
    }

    @ApiOperation("根据活动名称分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<FlashPromotion>> getItem(@RequestParam(value = "keyword", required = false) String keyword,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<FlashPromotion> page = flashPromotionService.list(keyword, pageSize, pageNum);
        return RestResponse.success(page);
    }
}

