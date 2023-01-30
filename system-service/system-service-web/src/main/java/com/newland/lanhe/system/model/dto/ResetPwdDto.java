package com.newland.lanhe.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 重置密码
 * Author: leell
 * Date: 2022/12/7 00:59:05
 */
@Data
@ApiModel(value="修改密码", description="修改密码对象")
public class ResetPwdDto {
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
