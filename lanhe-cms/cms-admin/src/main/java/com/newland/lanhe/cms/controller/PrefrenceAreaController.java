package com.newland.lanhe.cms.controller;

import com.newland.lanhe.cms.entity.PrefrenceArea;
import com.newland.lanhe.cms.model.dto.PrefrenceAreaProductRelationDto;
import com.newland.lanhe.cms.service.PrefrenceAreaService;
import com.newland.lanhe.model.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优选专区 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-02
 */
@Api(tags = "商品优选管理", description = "商品优选管理")
@RestController
@RequestMapping("/prefrenceArea")
public class PrefrenceAreaController {
    @Autowired
    private PrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<PrefrenceArea>> listAll() {
        List<PrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return RestResponse.ok(prefrenceAreaList);
    }

    @ApiOperation("批量关联专题")
    @RequestMapping(value = "/relateAndInsertList", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse relateAndInsertList(@RequestBody List<PrefrenceAreaProductRelationDto> productRelationInput,
                                            @RequestParam("productId") Long productId) {
        prefrenceAreaService.relateAndInsertList(productRelationInput, productId);
        return RestResponse.success();
    }

    @ApiOperation("批量更新关联专题")
    @RequestMapping(value = "/relateAndUpdateList", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse relateAndUpdateList(@RequestBody List<PrefrenceAreaProductRelationDto> productRelationInput,
                                            @RequestParam("productId") Long productId) {

        prefrenceAreaService.relateAndUpdateList(productRelationInput, productId);
        return RestResponse.success();
    }


    @ApiOperation("通过id查询关联专题")
    @RequestMapping(value = "/relationByProductId", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<PrefrenceAreaProductRelationDto>> relationByProductId(@RequestParam("productId") Long productId) {
        List<PrefrenceAreaProductRelationDto> productRelationList = prefrenceAreaService.relationByProductId(productId);
        return RestResponse.ok(productRelationList);
    }
}

