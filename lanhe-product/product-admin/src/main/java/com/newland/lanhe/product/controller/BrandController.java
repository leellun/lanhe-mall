package com.newland.lanhe.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.Brand;
import com.newland.lanhe.product.model.dto.BrandDto;
import com.newland.lanhe.product.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Api(tags = "商品品牌管理", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public RestResponse<List<Brand>> getList() {
        return RestResponse.ok(brandService.listAllBrand());
    }

    @ApiOperation(value = "添加品牌")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse create(@Validated @RequestBody BrandDto BrandDto) {
        brandService.createBrand(BrandDto);
        return RestResponse.success("添加成功");
    }

    @ApiOperation(value = "更新品牌")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable("id") Long id,
                               @Validated @RequestBody BrandDto BrandDto) {
        brandService.updateBrand(id, BrandDto);
        return RestResponse.success("更新成功");
    }

    @ApiOperation(value = "删除品牌")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public RestResponse delete(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
        return RestResponse.success("删除成功");
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<Page<Brand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                             @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return RestResponse.ok(brandService.listBrand(keyword, pageNo, pageSize));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<Brand> getItem(@PathVariable("id") Long id) {
        return RestResponse.ok(brandService.getBrand(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @DeleteMapping(value = "/delete/batch")
    @ResponseBody
    public RestResponse deleteBatch(@RequestParam("ids") List<Long> ids) {
        brandService.deleteBrand(ids);
        return RestResponse.success("删除成功");
    }

    @ApiOperation(value = "批量更新显示状态")
    @PutMapping(value = "/update/showStatus")
    @ResponseBody
    public RestResponse updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        brandService.updateShowStatus(ids, showStatus);
        return RestResponse.success("更新成功");
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @PutMapping(value = "/update/factoryStatus")
    @ResponseBody
    public RestResponse updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        brandService.updateFactoryStatus(ids, factoryStatus);
        return RestResponse.success("更新成功");
    }
}

