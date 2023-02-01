package com.newland.lanhe.uaa.service;

import com.newland.lanhe.enumeration.ResultCode;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.model.RestResponse;
import com.newland.lanhe.system.agent.SysUserApiAgent;
import com.newland.lanhe.system.dto.LoginDTO;
import com.newland.lanhe.uaa.exception.RestOauth2Exception;
import com.newland.lanhe.uaa.model.UnifiedUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户认证信息查询service
 * Author: leell
 * Date: 2022/12/3 16:23:51
 */
@Service
public class UserDetailsAuthenticationService {
    @Autowired
    private SysUserApiAgent sysUserApiAgent;

    public UserDetails authentication(UsernamePasswordAuthenticationToken token) {
        String usernmae = token.getName();
        if (StringUtils.isEmpty(usernmae)) {
            throw new BadCredentialsException("账户不能为空");
        }
        if (token.getCredentials() == null) {
            throw new BadCredentialsException("密码不能为空");
        }
        String presentedPassword = token.getCredentials().toString();

        //远程调用统一账户服务，进行账户密码校验
        LoginDTO accountLoginDTO = new LoginDTO();
        accountLoginDTO.setUsername(usernmae);
        accountLoginDTO.setPassword(presentedPassword);
        RestResponse<LoginUser> response = sysUserApiAgent.login(accountLoginDTO);

        if (!response.getCode().equals(ResultCode.SUCCESS.getCode())) {
            throw new RestOauth2Exception(response);
        }
        LoginUser accountDTO = response.getData();
        UnifiedUserDetails userDetails = new UnifiedUserDetails(accountDTO.getUsername(), presentedPassword, accountDTO);
        return userDetails;
    }
}
