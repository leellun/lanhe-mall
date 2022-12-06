package com.newland.lanhe.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LanheGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanheGatewayApplication.class, args);
    }

}
