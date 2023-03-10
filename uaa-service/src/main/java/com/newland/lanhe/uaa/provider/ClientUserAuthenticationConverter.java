package com.newland.lanhe.uaa.provider;

import com.newland.lanhe.constant.Constant;
import com.newland.lanhe.uaa.model.UnifiedUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 认证转换器
 * @Date: 2022/12/3 17:50:49
 * @author leell
 */
public class ClientUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getPrincipal() instanceof UnifiedUserDetails) {
            UnifiedUserDetails unifiedUserDetails = (UnifiedUserDetails) authentication.getPrincipal();
            response.put(Constant.KEY_USERID, unifiedUserDetails.getUserId());
        }
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

}
