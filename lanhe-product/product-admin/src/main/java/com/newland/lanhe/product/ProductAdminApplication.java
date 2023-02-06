package com.newland.lanhe.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.newland.lanhe"})
@EnableFeignClients(basePackages = {"com.newland.lanhe"})
@EnableDiscoveryClient
@EnableTransactionManagement
public class ProductAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductAdminApplication.class, args);
    }

}
