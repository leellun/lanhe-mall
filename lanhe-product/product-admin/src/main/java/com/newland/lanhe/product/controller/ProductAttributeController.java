package com.newland.lanhe.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.ProductAttribute;
import com.newland.lanhe.product.model.dto.ProductAttributeDto;
import com.newland.lanhe.product.model.vo.ProductAttrInfoVo;
import com.newland.lanhe.product.service.ProductAttributeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Api(tags = "商品属性管理", description = "商品属性管理")
@RestController
@RequestMapping("/productAttribute")
public class ProductAttributeController {
    @Autowired
    private ProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @GetMapping(value = "/list/{cid}")
    @ResponseBody
    public RestResponse<Page<ProductAttribute>> getList(@PathVariable Long cid,
                                                        @ApiParam("0表示属性，1表示参数") @RequestParam(value = "type") Integer type,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        Page<ProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return RestResponse.ok(productAttributeList);
    }
    @ApiOperation("根据分类获取所有规格属性和销售属性")
    @GetMapping(value = "/all/{cid}")
    @ResponseBody
    public RestResponse<List<ProductAttribute>> getAllList(@PathVariable Long cid,@ApiParam("0表示属性，1表示参数") @RequestParam(value = "type") Integer type) {

        List<ProductAttribute> productAttributeList = productAttributeService.getList(cid,type);
        return RestResponse.ok(productAttributeList);
    }

    @ApiOperation("添加商品属性信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse create(@RequestBody ProductAttributeDto productAttributeParam) {
        productAttributeService.create(productAttributeParam);
        return RestResponse.success("添加成功");
    }

    @ApiOperation("修改商品属性信息")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody ProductAttributeDto productAttributeParam) {
        productAttributeService.update(id, productAttributeParam);
        return RestResponse.success("更新成功");
    }

    @ApiOperation("查询单个商品属性")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<ProductAttribute> getItem(@PathVariable Long id) {
        ProductAttribute productAttribute = productAttributeService.getItem(id);
        return RestResponse.ok(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public RestResponse delete(@RequestBody List<Long> ids) {
        productAttributeService.delete(ids);
        return RestResponse.success("删除成功");
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping(value = "/attrInfo/{productCategoryId}")
    @ResponseBody
    public RestResponse<List<ProductAttrInfoVo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfoVo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return RestResponse.ok(productAttrInfoList);
    }
}

