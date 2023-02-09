package com.newland.lanhe.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.ProductCategory;
import com.newland.lanhe.product.model.dto.ProductCategoryDto;
import com.newland.lanhe.product.model.vo.ProductCategoryWithChildrenItemVo;
import com.newland.lanhe.product.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Api(tags = "商品分类管理", description = "商品分类管理")
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation("添加产品分类")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse create(@Validated @RequestBody ProductCategoryDto productCategoryDto,
                               BindingResult result) {
        productCategoryService.create(productCategoryDto);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改商品分类")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable Long id,
                               @Validated
                               @RequestBody ProductCategoryDto productCategoryDto,
                               BindingResult result) {
        productCategoryService.update(id, productCategoryDto);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping(value = "/list/{parentId}")
    @ResponseBody
    public RestResponse<Page<ProductCategory>> getList(@PathVariable Long parentId,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<ProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNo);
        return RestResponse.ok(productCategoryList);
    }

    @ApiOperation("根据id获取商品分类")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<ProductCategory> getItem(@PathVariable Long id) {
        ProductCategory productCategory = productCategoryService.getItem(id);
        return RestResponse.ok(productCategory);
    }

    @ApiOperation("删除商品分类")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public RestResponse delete(@PathVariable Long id) {
        productCategoryService.delete(id);
        return RestResponse.success("刪除成功");
    }

    @ApiOperation("修改导航栏显示状态")
    @PutMapping(value = "/update/navStatus")
    @ResponseBody
    public RestResponse updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        productCategoryService.updateNavStatus(ids, navStatus);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("修改显示状态")
    @PutMapping(value = "/update/showStatus")
    @ResponseBody
    public RestResponse updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        productCategoryService.updateShowStatus(ids, showStatus);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping(value = "/list/withChildren")
    @ResponseBody
    public RestResponse<List<ProductCategoryWithChildrenItemVo>> listWithChildren() {
        List<ProductCategoryWithChildrenItemVo> list = productCategoryService.listWithChildren();
        return RestResponse.ok(list);
    }
    @ApiOperation("查询子分类")
    @GetMapping(value = "/sub/{pid}")
    @ResponseBody
    public RestResponse<List<ProductCategory>> getSubCategorys(@PathVariable("pid") Long pid) {
        return RestResponse.ok(productCategoryService.getSubCategorys(pid));
    }
}

