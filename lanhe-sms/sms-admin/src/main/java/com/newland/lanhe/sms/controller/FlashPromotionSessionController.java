package com.newland.lanhe.sms.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.FlashPromotionSession;
import com.newland.lanhe.sms.model.vo.FlashPromotionSessionDetailVo;
import com.newland.lanhe.sms.service.FlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "限时购场次管理", description = "限时购场次管理")
@RestController
@RequestMapping("/flashPromotionSession")
public class FlashPromotionSessionController {
    @Autowired
    private FlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("添加场次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody FlashPromotionSession promotionSession) {
        flashPromotionSessionService.create(promotionSession);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改场次")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody FlashPromotionSession promotionSession) {
        flashPromotionSessionService.update(id, promotionSession);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改启用状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateStatus(@PathVariable Long id, Integer status) {
        flashPromotionSessionService.updateStatus(id, status);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("删除场次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@PathVariable Long id) {
        flashPromotionSessionService.delete(id);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("获取场次详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<FlashPromotionSession> getItem(@PathVariable Long id) {
        FlashPromotionSession promotionSession = flashPromotionSessionService.getItem(id);
        return RestResponse.success(promotionSession);
    }

    @ApiOperation("获取全部场次")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<FlashPromotionSession>> list() {
        List<FlashPromotionSession> promotionSessionList = flashPromotionSessionService.list();
        return RestResponse.success(promotionSessionList);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<FlashPromotionSessionDetailVo>> selectList(Long flashPromotionId) {
        List<FlashPromotionSessionDetailVo> promotionSessionList = flashPromotionSessionService.selectList(flashPromotionId);
        return RestResponse.success(promotionSessionList);
    }
}

