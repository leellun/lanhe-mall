package com.newland.lanhe.system;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Author: leell
 * Date: 2023/1/15 17:27:09
 */
@Configuration
@ComponentScan
@EnableFeignClients
public class UserAgentConfig {
}
