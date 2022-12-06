package com.newland.lanhe.security.enumeration;

public enum SecurityErrorCode  {
    NOT_LOGIN(1201,"未登录"),
    ACCESS_DENIED(1403,"没有权限");

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private SecurityErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
