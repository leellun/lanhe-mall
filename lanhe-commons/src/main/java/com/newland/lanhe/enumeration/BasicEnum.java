package com.newland.lanhe.enumeration;

/**
 * 基础枚举常量 1：true 0：false
 * Author: leell
 * Date: 2022/12/7 00:03:46
 */
public enum BasicEnum {
    YES(1,"启用/是"),
    NO(0,"关闭/否"),
            ;


    private Integer code;
    private String desc;

    BasicEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
