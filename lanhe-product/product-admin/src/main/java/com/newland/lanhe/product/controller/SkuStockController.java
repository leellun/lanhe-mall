package com.newland.lanhe.product.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.SkuStock;
import com.newland.lanhe.product.service.SkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Api(tags = "sku商品库存管理", description = "sku商品库存管理")
@RestController
@RequestMapping("/skuStock")
public class SkuStockController {
    @Autowired
    private SkuStockService skuStockService;

    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<SkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<SkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return RestResponse.success(skuStockList);
    }
    @ApiOperation("批量更新库存信息")
    @RequestMapping(value ="/update/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse update(@PathVariable Long pid, @RequestBody List<SkuStock> skuStockList){
        skuStockService.update(pid,skuStockList);
        return RestResponse.success("更新成功");
    }
}

