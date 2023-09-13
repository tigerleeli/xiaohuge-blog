package com.example.gatewaydocservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDocServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayDocServiceApplication.class, args);
    }

}
