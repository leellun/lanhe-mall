package com.newland.lanhe.cms.agent;

import com.newland.lanhe.cms.model.dto.SubjectProductRelationDto;
import com.newland.lanhe.model.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cms-admin",path = "subject",contextId = "subject")
public interface SubjectProductRelationAgent {
    /**
     * 关联优选
     *
     * @param productRelationInputs
     */
    @RequestMapping(value = "/relateAndInsertList", method = RequestMethod.POST)
    RestResponse relateAndInsertList(@RequestBody List<SubjectProductRelationDto> productRelationInputs, @RequestParam("productId") Long productId);

    /**
     * 批量更新关联优选
     * @param productRelationInputs
     * @param productId
     */
    @RequestMapping(value = "/relateAndUpdateList", method = RequestMethod.POST)
    RestResponse relateAndUpdateList(@RequestBody List<SubjectProductRelationDto> productRelationInputs,@RequestParam("productId") Long productId);


    /**
     * 通过id查询关联优选
     * @param productId
     * @return
     */
    @RequestMapping(value = "/relationByProductId", method = RequestMethod.GET)
    RestResponse<List<SubjectProductRelationDto>> relationByProductId(@RequestParam("productId") Long productId ) ;

}