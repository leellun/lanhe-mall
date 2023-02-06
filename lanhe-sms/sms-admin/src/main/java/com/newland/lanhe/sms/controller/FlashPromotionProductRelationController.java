package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.FlashPromotionProductRelation;
import com.newland.lanhe.sms.model.vo.FlashPromotionProductVo;
import com.newland.lanhe.sms.service.FlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "限时购和商品关系管理", description = "限时购和商品关系管理")
@RestController
@RequestMapping("/flashPromotionProductRelation")
public class FlashPromotionProductRelationController {
    @Autowired
    private FlashPromotionProductRelationService relationService;

    @ApiOperation("批量选择商品添加关联")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody List<FlashPromotionProductRelation> relationList) {
        relationService.create(relationList);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改关联相关信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody FlashPromotionProductRelation relation) {
        relationService.update(id, relation);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("删除关联")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@PathVariable Long id) {
        relationService.delete(id);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("获取管理商品促销信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<FlashPromotionProductRelation> getItem(@PathVariable Long id) {
        FlashPromotionProductRelation relation = relationService.getItem(id);
        return RestResponse.success(relation);
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<FlashPromotionProductVo>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                            @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<FlashPromotionProductVo> page = relationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return RestResponse.success(page);
    }
}

