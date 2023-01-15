package com.newland.lanhe.security.utils;

import com.newland.lanhe.model.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    /**
     * 获取当前登录用户
     */
    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return loginUser;
    }

}
