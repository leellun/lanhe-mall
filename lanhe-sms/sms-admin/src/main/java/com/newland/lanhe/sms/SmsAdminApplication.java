package com.newland.lanhe.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.newland.lanhe")
@EnableFeignClients(basePackages = "com.newland.lanhe")
@EnableDiscoveryClient
public class SmsAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsAdminApplication.class, args);
    }

}
