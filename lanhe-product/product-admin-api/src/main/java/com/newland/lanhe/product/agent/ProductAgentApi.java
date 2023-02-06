package com.newland.lanhe.product.agent;

import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.product.vo.ProductOutputVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 商品管理代理
 * @author leellun
 */
@FeignClient(name = "product-admin", path = "product")
public interface ProductAgentApi {

    /**
     * 通过id查询商品信息
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/getProductByProductId")
    RestResponse<ProductOutputVo> getProductByProductId(@RequestParam("productId") Long productId);


}
