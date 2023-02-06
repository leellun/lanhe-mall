package com.newland.lanhe.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.ProductAttributeCategory;
import com.newland.lanhe.product.model.vo.ProductAttributeCategoryItemVo;
import com.newland.lanhe.product.service.ProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@RestController
@Api(tags = "商品属性分类管理", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class ProductAttributeCategoryController {
    @Autowired
    private ProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse create(@RequestParam String name) {
        productAttributeCategoryService.create(name);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改商品属性分类")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestParam String name) {
        productAttributeCategoryService.update(id, name);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("删除单个商品属性分类")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public RestResponse delete(@PathVariable Long id) {
        productAttributeCategoryService.delete(id);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("获取单个商品属性分类信息")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<ProductAttributeCategory> getItem(@PathVariable Long id) {
        return RestResponse.ok(productAttributeCategoryService.getItem(id));
    }

    @ApiOperation("分页获取所有商品属性分类")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<Page<ProductAttributeCategory>> getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        return RestResponse.ok(productAttributeCategoryService.getList(pageSize, pageNum));
    }
    @ApiOperation("获取所有商品属性分类")
    @GetMapping(value = "/all")
    @ResponseBody
    public RestResponse<List<ProductAttributeCategory>> getTotalList() {
        return RestResponse.ok(productAttributeCategoryService.list());
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @GetMapping(value = "/list/withAttr")
    @ResponseBody
    public RestResponse<List<ProductAttributeCategoryItemVo>> getListWithAttr() {
        List<ProductAttributeCategoryItemVo> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return RestResponse.ok(productAttributeCategoryResultList);
    }
}

