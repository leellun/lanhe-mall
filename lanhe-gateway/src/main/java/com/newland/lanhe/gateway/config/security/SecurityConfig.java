package com.newland.lanhe.gateway.config.security;

import com.newland.lanhe.gateway.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

/**
 * security支持
 */
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationManager jwtAuthenticationManager;
    @Autowired
    private JwtBearerTokenAuthenticationConverter jwtBearerTokenAuthenticationConverter;
    @Bean
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers("/uaa/druid/**").denyAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/*/swagger-ui.html", "/*/webjars/**", "/*/swagger-resources/**", "/*/v2/*", "/*/csrf", "/*/").permitAll()
                .pathMatchers("/uaa/**").permitAll()
                .anyExchange().authenticated()
                .and().addFilterAt(webFilter(),SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler()).authenticationEntryPoint(new RestOAuth2AuthExceptionEntryPoint())
                .and().csrf().disable().build();
    }
    public AuthenticationWebFilter webFilter() {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(jwtBearerTokenAuthenticationConverter);
        return authenticationWebFilter;
    }
}
