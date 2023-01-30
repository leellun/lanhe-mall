package com.newland.lanhe.uaa.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.newland.lanhe.constant.Constant;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.uaa.entity.OauthClientDetails;
import com.newland.lanhe.uaa.mapper.OauthClientDetailsMapper;
import com.newland.lanhe.uaa.model.UnifiedUserDetails;
import com.newland.redis.utils.RedisUtil;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Map;

/**
 * Date: 2023/1/16 22:26:23
 * @author leell
 */
public class RedisJwtTokenStore extends JwtTokenStore {
    private RedisUtil redisUtil;
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    /**
     * Create a JwtTokenStore with this token enhancer (should be shared with the DefaultTokenServices if used).
     *
     * @param jwtTokenEnhancer
     */
    public RedisJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer, RedisUtil redisUtil, OauthClientDetailsMapper oauthClientDetailsMapper) {
        super(jwtTokenEnhancer);
        this.redisUtil = redisUtil;
        this.oauthClientDetailsMapper = oauthClientDetailsMapper;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        super.storeAccessToken(token, authentication);
        this.setUserLoginInfo(authentication);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        super.storeRefreshToken(refreshToken, authentication);
        this.setUserLoginInfo(authentication);
    }

    private void setUserLoginInfo(OAuth2Authentication authentication) {
        UnifiedUserDetails unifiedUserDetails = (UnifiedUserDetails) authentication.getUserAuthentication().getPrincipal();
        LoginUser loginUser = unifiedUserDetails.getLoginUser();
        redisUtil.set(Constant.USER_LOGIN_INFO + loginUser.getUserId(), loginUser, getExpireTime(authentication));
    }

    private Long getExpireTime(OAuth2Authentication authentication) {
        Map<String, String> map = (Map<String, String>) authentication.getUserAuthentication().getDetails();
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectOne(Wrappers.<OauthClientDetails>lambdaQuery().select(OauthClientDetails::getAccessTokenValidity, OauthClientDetails::getRefreshTokenValidity).eq(OauthClientDetails::getClientId, map.get(AccessTokenConverter.CLIENT_ID)));
        long expirieTime = 0L;
        if (oauthClientDetails.getAccessTokenValidity() != null) {
            expirieTime = oauthClientDetails.getAccessTokenValidity() * 1000L;
        }
        if (oauthClientDetails.getRefreshTokenValidity() != null) {
            expirieTime = Math.max(expirieTime, oauthClientDetails.getRefreshTokenValidity() * 1000L);
        }
        return expirieTime;
    }
}
