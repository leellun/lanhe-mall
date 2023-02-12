package com.newland.lanhe.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.entity.Product;
import com.newland.lanhe.product.model.dto.ProductDto;
import com.newland.lanhe.product.model.dto.ProductQueryDto;
import com.newland.lanhe.product.model.vo.ProductDetailVo;
import com.newland.lanhe.product.service.ProductService;
import com.newland.lanhe.product.vo.ProductOutputVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-01
 */
@Api(tags = "商品管理", description = "商品管理")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @ApiOperation("商品列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<Page<Product>> getList(ProductQueryDto productQueryDto) {
        Page<Product> productPage = productService.list(productQueryDto);
        return RestResponse.ok(productPage);
    }
    @ApiOperation("创建商品")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse add(@RequestBody ProductDto productDto) {
        productService.add(productDto);
        return RestResponse.success("添加成功");
    }
    @ApiOperation("根据商品id获取商品编辑信息")
    @GetMapping(value = "/updateInfo/{id}")
    @ResponseBody
    public RestResponse<ProductDetailVo> getUpdateInfo(@PathVariable Long id) {
        ProductDetailVo productResult = productService.getProductDetail(id);
        return RestResponse.ok(productResult);
    }

    @ApiOperation("更新商品")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productService.update(id, productDto);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @GetMapping(value = "/simpleList")
    @ResponseBody
    public RestResponse<List<Product>> getList(@RequestParam("keyword") String keyword) {
        List<Product> productList = productService.list(keyword);
        return RestResponse.ok(productList);
    }

    @ApiOperation("批量修改审核状态")
    @PutMapping(value = "/update/verifyStatus")
    @ResponseBody
    public RestResponse updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail) {
        productService.updateVerifyStatus(ids, verifyStatus, detail);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("批量上下架")
    @PutMapping(value = "/update/publishStatus")
    @ResponseBody
    public RestResponse updatePublishStatus(@RequestParam(value = "ids") List<Long> ids,
                                            @RequestParam(value = "publishStatus") Integer publishStatus) {
        productService.updatePublishStatus(ids, publishStatus);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("批量推荐商品")
    @PutMapping(value = "/update/recommendStatus")
    @ResponseBody
    public RestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        productService.updateRecommendStatus(ids, recommendStatus);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("批量设为新品")
    @PutMapping(value = "/update/newStatus")
    @ResponseBody
    public RestResponse updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        productService.updateNewStatus(ids, newStatus);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("批量修改删除状态")
    @PutMapping(value = "/update/deleteStatus")
    @ResponseBody
    public RestResponse updateDeleteStatus(@RequestBody List<Long> ids) {
        productService.delete(ids);
        return RestResponse.success("删除成功");
    }


    @ApiOperation("通过id查询商品信息")
    @GetMapping(value = "/getProductByProductId")
    @ResponseBody
    public RestResponse<ProductOutputVo> getProductByProductId(@RequestParam("productId") Long productId ) {
        ProductOutputVo pmsProduct = productService.getProductByProductId(productId);
        return RestResponse.ok(pmsProduct);
    }
}

