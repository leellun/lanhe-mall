package com.newland.lanhe.gateway.config;

import com.alibaba.fastjson.JSON;
import com.newland.lanhe.model.RestResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 访问拒绝，没有权限
 * 在没有token的情况下
 */
public class RestAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        RestResponse restResponse = RestResponse.error(HttpStatus.UNAUTHORIZED.value(), "没有权限");
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(restResponse).getBytes());
        return response.writeWith(Mono.just(buffer));
    }
}
