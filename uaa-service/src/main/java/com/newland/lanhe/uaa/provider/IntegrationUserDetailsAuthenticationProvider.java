package com.newland.lanhe.uaa.provider;

import com.newland.lanhe.uaa.service.UserDetailsAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 统一用户认证处理
 * Author: leell
 * Date: 2022/10/17 20:47:51
 */
@Component
public class IntegrationUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private UserDetailsAuthenticationService userDetailsAuthenticationService;

    /**
     * 允许子类为给定的身份验证请求执行返回(或缓存)UserDetails的任何附加检查。
     * 一般来说，子类至少会比较Authentication.getCredentials()和UserDetails.getPassword()。
     * 如果需要自定义逻辑来比较UserDetails和/或UsernamePasswordAuthenticationToken的其他属性，这些属性也应该出现在这个方法中
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    /**
     * 通过authenticationToken携带信息获取UserDetails
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken)) {
            throw new InternalAuthenticationServiceException("authenticationType is blank");
        }

        UserDetails userDetails = userDetailsAuthenticationService.authentication(authenticationToken);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("用户认证信息为空");
        }
        return userDetails;
    }
}
