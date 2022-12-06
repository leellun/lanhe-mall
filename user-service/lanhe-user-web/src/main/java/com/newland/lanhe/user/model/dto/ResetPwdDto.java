package com.newland.lanhe.user.model.dto;

import lombok.Data;

/**
 * 重置密码
 * Author: leell
 * Date: 2022/12/7 00:59:05
 */
@Data
public class ResetPwdDto {
    private String password;
    private String newPassword;
}
