package com.newland.lanhe.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.security.utils.SecurityUtil;
import com.newland.lanhe.user.dto.LoginDTO;
import com.newland.lanhe.user.model.dto.ResetPwdDto;
import com.newland.lanhe.user.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "accountLoginDTO", value = "登录信息", required = true,
            dataType = "AccountLoginDTO", paramType = "body")
    @PostMapping(value = "/login")
    public RestResponse login(@RequestBody @Valid LoginDTO loginDTO) {
        LoginUser user = sysUserService.login(loginDTO);
        return RestResponse.success(user);
    }

    @ApiOperation("用户登出")
    @PostMapping(value = "/loginOut")
    public RestResponse loginOut() {
        sysUserService.loginOut(SecurityUtil.getUser());
        return RestResponse.success();
    }

    @PostMapping(value = "/resetPwd")
    @ApiOperation(value = "重置密码")
    public RestResponse resetPwd(@RequestBody ResetPwdDto resetPwdDto) {
        sysUserService.resetPwd(SecurityUtil.getUser(), resetPwdDto);
        return RestResponse.success();
    }

    @GetMapping
    @ApiOperation(value = "获取分页列表")
    public RestResponse list(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return RestResponse.success(sysUserService.getList(pageNo, pageSize));
    }
}

