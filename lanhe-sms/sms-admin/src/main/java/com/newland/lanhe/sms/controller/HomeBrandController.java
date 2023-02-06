package com.newland.lanhe.sms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.sms.entity.HomeBrand;
import com.newland.lanhe.sms.service.HomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "首页品牌管理", description = "首页品牌管理")
@RestController
@RequestMapping("/homeBrand")
public class HomeBrandController {
    @Autowired
    private HomeBrandService homeBrandService;

    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse create(@RequestBody List<HomeBrand> homeBrandList) {
        homeBrandService.create(homeBrandList);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("修改品牌排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateSort(@PathVariable Long id, Integer sort) {
        homeBrandService.updateSort(id, sort);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("批量删除推荐品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse delete(@RequestParam("ids") List<Long> ids) {
        homeBrandService.delete(ids);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        homeBrandService.updateRecommendStatus(ids, recommendStatus);
        return RestResponse.success("操作成功");
    }

    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Page<HomeBrand>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                              @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<HomeBrand> page = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return RestResponse.success(page);
    }
}

