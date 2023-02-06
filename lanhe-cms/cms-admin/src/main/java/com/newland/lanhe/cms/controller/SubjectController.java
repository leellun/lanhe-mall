package com.newland.lanhe.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.cms.entity.Subject;
import com.newland.lanhe.cms.model.dto.SubjectProductRelationDto;
import com.newland.lanhe.cms.service.SubjectService;
import com.newland.lanhe.model.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-02
 */
@Api(tags = "商品专题管理", description = "商品专题管理")
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<Subject>> listAll() {
        List<Subject> subjectList = subjectService.listAll();
        return RestResponse.ok(subjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<Subject>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<Subject> page = subjectService.list(keyword, pageNum, pageSize);
        return RestResponse.ok(page);
    }

    @ApiOperation("批量插入关联优选")
    @RequestMapping(value = "/relateAndInsertList", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse relateAndInsertList(@RequestBody List<SubjectProductRelationDto> productRelationInputs ,
                                            @RequestParam("productId") Long productId ) {
        subjectService.relateAndInsertList(productRelationInputs,productId);
        return RestResponse.success();
    }

    @ApiOperation("批量更新关联优选")
    @RequestMapping(value = "/relateAndUpdateList", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse relateAndUpdateList(@RequestBody List<SubjectProductRelationDto> productRelationInputs ,
                                            @RequestParam("productId") Long productId ) {
        subjectService.relateAndUpdateList(productRelationInputs,productId);
        return RestResponse.success();
    }

    @ApiOperation("通过id查询关联优选")
    @RequestMapping(value = "/relationByProductId", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<SubjectProductRelationDto>> relationByProductId(@RequestParam("productId") Long productId ) {
        List<SubjectProductRelationDto> productRelationList = subjectService.relationByProductId(productId);
        return RestResponse.ok(productRelationList);
    }
}

