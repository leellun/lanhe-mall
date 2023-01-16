package com.newland.lanhe.uua.model;


import com.newland.lanhe.model.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


public class UnifiedUserDetails implements UserDetails {

    private static final long serialVersionUID = 3957586021470480642L;
    /**
     * 用户的授权集合
     */
    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    private String username;

    private String password;
    /**
     * 登录用户
     */
    private LoginUser loginUser;


    public UnifiedUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UnifiedUserDetails(String username, String password, LoginUser loginUser) {
        this.username = username;
        this.password = password;
        this.loginUser = loginUser;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList();
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public Long getUserId() {
        return loginUser != null ? loginUser.getUserId() : null;
    }

    /* 账户是否未过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*账户是否未锁定 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 密码是否未过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*账户是否启用,默认true (启用)*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}