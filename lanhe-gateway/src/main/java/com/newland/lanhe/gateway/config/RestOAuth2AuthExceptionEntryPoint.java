package com.newland.lanhe.gateway.config;

import com.alibaba.fastjson.JSON;
import com.newland.lanhe.model.RestResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 认证异常端点
 */
public class RestOAuth2AuthExceptionEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        RestResponse restResponse = RestResponse.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(restResponse).getBytes());
        return response.writeWith(Mono.just(buffer));
    }
}
