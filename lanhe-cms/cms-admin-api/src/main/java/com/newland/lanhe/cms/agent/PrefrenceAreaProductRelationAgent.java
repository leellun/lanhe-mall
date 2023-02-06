package com.newland.lanhe.cms.agent;

import com.newland.lanhe.cms.model.dto.PrefrenceAreaProductRelationDto;
import com.newland.lanhe.model.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author mtcarpenter
 * @github https://github.com/mtcarpenter/mall-cloud-alibaba
 * @desc 微信公众号：山间木匠
 */
@FeignClient(name = "admin-cms", path = "prefrenceArea",contextId = "prefrenceArea")
public interface PrefrenceAreaProductRelationAgent {

    /**
     * 批量关联商品
     *
     * @param productRelationInput
     * @return
     */
    @RequestMapping(value = "/relateAndInsertList", method = RequestMethod.POST)
    RestResponse relateAndInsertList(@RequestBody List<PrefrenceAreaProductRelationDto> productRelationInput, @RequestParam("productId") Long productId);


    /**
     * 批量更新关联商品
     *
     * @param productRelationInputs
     * @param productId
     */
    @RequestMapping(value = "/relateAndUpdateList", method = RequestMethod.POST)
    RestResponse relateAndUpdateList(@RequestBody List<PrefrenceAreaProductRelationDto> productRelationInputs, @RequestParam("productId") Long productId);


    /**
     * 通过id查询关联专题
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/relationByProductId", method = RequestMethod.GET)
    RestResponse<List<PrefrenceAreaProductRelationDto>> relationByProductId(@RequestParam("productId") Long productId);
}
