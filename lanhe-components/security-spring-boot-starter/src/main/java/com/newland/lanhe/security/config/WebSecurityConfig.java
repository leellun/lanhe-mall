package com.newland.lanhe.security.config;

import com.newland.lanhe.security.AuthenticationFilter;
import com.newland.lanhe.security.handler.LoginUrlAuthenticationEntryPoint;
import com.newland.lanhe.security.handler.NewlandAccessDeniedHandler;
import com.newland.lanhe.security.properties.HttpItem;
import com.newland.lanhe.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 资源服务配置
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/*", "/csrf", "/").antMatchers(HttpMethod.OPTIONS);
    }

    /**
     * 请求配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
        if (securityProperties.getPermitItems() != null && securityProperties.getPermitItems().length > 0) {
            for (HttpItem httpItem : securityProperties.getPermitItems()) {
                if (httpItem.getMethod() == null) {
                    http.authorizeRequests().antMatchers(httpItem.getUrl()).permitAll();
                } else {
                    http.authorizeRequests().antMatchers(HttpMethod.resolve(httpItem.getMethod()), httpItem.getUrl()).permitAll();
                }
            }
            http.authorizeRequests().anyRequest().authenticated();
        } else {
            http.authorizeRequests().anyRequest().authenticated();
        }
//        http.authorizeRequests().anyRequest().permitAll();
        http.exceptionHandling()
                .accessDeniedHandler(new NewlandAccessDeniedHandler())
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint())
                .and().headers().cacheControl().disable();
    }

}
