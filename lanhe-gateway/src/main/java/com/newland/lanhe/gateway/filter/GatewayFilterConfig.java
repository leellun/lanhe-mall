package com.newland.lanhe.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.lanhe.constant.Constant;
import com.newland.lanhe.model.LoginUser;
import com.newland.lanhe.utils.Base64Utils;
import com.newland.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Slf4j
public class GatewayFilterConfig implements GlobalFilter, Ordered {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(requestUrl);
        //2 检查token是否存在
        String token = getToken(exchange);
        if (StringUtils.isBlank(token)) {
            return chain.filter(exchange);
        } else {
            try {
                //3 判断是否是有效的token
                OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
                Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
                //获取用户权限
                Number userId = (Number) additionalInformation.get(Constant.KEY_USERID);
                LoginUser loginUser = redisUtil.get(Constant.USER_LOGIN_INFO + userId);
                if (loginUser != null) {
                    ServerHttpRequest tokenRequest = exchange.getRequest().mutate().header("json-token", Base64Utils.encodeToString(JSON.toJSONString(loginUser))).build();
                    ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
                    return chain.filter(build);
                } else {
                    return chain.filter(exchange);
                }
            } catch (InvalidTokenException e) {
                log.info("无效的token: {}", token);
                return chain.filter(exchange);
            }
        }
    }


    /**
     * 获取token
     */
    private String getToken(ServerWebExchange exchange) {
        String tokenStr = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(tokenStr)) {
            return null;
        }
        String token = tokenStr.split(" ")[1];
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token;
    }


    /**
     * 无效的token
     */
    private Mono<Void> invalidTokenMono(ServerWebExchange exchange) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.UNAUTHORIZED.value());
        json.put("data", "无效的token");
        return buildReturnMono(json, exchange);
    }

    private Mono<Void> buildReturnMono(JSONObject json, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bits = json.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }


    @Override
    public int getOrder() {
        return 0;
    }
}