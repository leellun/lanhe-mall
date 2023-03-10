package com.newland.lanhe.gateway.config;

import com.newland.lanhe.gateway.model.JwtTokenAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * token认证
 * Author: leell
 * Date: 2022/10/16 19:53:24
 *
 * @author admin
 */
@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof JwtTokenAuthenticationToken)
                .cast(JwtTokenAuthenticationToken.class)
                .flatMap(accessToken -> {
                    if (accessToken == null) {
                        return Mono.error(new InvalidTokenException("无效的token！"));
                    } else if (!accessToken.isAuthenticated()) {
                        return Mono.error(new InvalidTokenException("token已经过期！"));
                    } else {
                        return Mono.just(accessToken);
                    }
                });
    }
}
