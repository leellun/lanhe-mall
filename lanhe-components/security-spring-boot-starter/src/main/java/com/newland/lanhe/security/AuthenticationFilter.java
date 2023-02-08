package com.newland.lanhe.security;

import com.alibaba.fastjson2.JSON;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.utils.Base64Utils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String json = request.getHeader("json-token");
        if (!StringUtils.isEmpty(json)) {
            LoginUser user = JSON.parseObject(Base64Utils.decodeToString(json), LoginUser.class);
            //身份信息、权限信息填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
                    AuthorityUtils.createAuthorityList(user.getAuthorities().toArray(new String[]{})));
            //创建details
            authenticationToken.setDetails(user);
            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
