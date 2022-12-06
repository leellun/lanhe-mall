package com.newland.lanhe.user.controller;


import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.user.dto.LoginDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "accountLoginDTO", value = "登录信息", required = true,
            dataType = "AccountLoginDTO", paramType = "body")
    @PostMapping(value = "/login")
    public LoginUser login(@RequestBody LoginDTO loginDTO) {
        return null;
    }

}

