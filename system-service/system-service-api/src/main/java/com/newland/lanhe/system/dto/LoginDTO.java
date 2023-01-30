package com.newland.lanhe.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录dto
 * Author: leell
 * Date: 2022/12/6 15:09:37
 */
@Data
@ApiModel(value = "LoginDTO", description = "账户注册信息")
public class LoginDTO {

    @NotEmpty(message = "请输入用户名")
    @ApiModelProperty("用户名")
    private String username;
    @NotEmpty(message = "请输入密码")
    @ApiModelProperty("密码")
    private String password;

}
