package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.HomeRecommendProduct;
import com.newland.lanhe.sms.service.HomeRecommendProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "首页人气推荐管理", description = "首页人气推荐管理")
@RestController
@RequestMapping("/homeRecommendProduct")
public class HomeRecommendProductController {
    @Autowired
    private HomeRecommendProductService recommendProductService;

    @ApiOperation("添加首页推荐")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody List<HomeRecommendProduct> homeBrandList) {
        recommendProductService.create(homeBrandList);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateSort(@PathVariable Long id, Integer sort) {
        recommendProductService.updateSort(id, sort);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@RequestParam("ids") List<Long> ids) {
        recommendProductService.delete(ids);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        recommendProductService.updateRecommendStatus(ids, recommendStatus);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<HomeRecommendProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                         @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<HomeRecommendProduct> page = recommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return RestResponse.success(page);
    }
}

