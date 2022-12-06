package com.newland.lanhe.user.enums;

import com.newland.lanhe.enumeration.ErrorCode;

/**
 * 服务模块错误
 * Author: leell
 * Date: 2022/12/6 23:57:22
 */
public enum UserServiceErrorEnum implements ErrorCode {
    USER_NOT_EXIST(1001,"用户不存在！"),
    USER_PASSWORD_ERROR(1002,"用户密码错误"),
    USER_FORBIDDEN(1003,"用户未启用"),
    ;


    private Integer code;
    private String desc;

    UserServiceErrorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
