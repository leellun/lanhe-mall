package com.newland.lanhe.order.controller;


import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.order.entity.CompanyAddress;
import com.newland.lanhe.order.service.CompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2023-02-03
 */
@Api(tags = "收货地址管理", description = "收货地址管理")
@RestController
@RequestMapping("/companyAddress")
public class CompanyAddressController {
    @Autowired
    private CompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<List<CompanyAddress>> list() {
        List<CompanyAddress> companyAddressList = companyAddressService.list();
        return RestResponse.success(companyAddressList);
    }
}

