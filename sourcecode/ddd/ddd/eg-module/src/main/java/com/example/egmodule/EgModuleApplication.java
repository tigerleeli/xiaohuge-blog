package com.example.egmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@SpringBootApplication
public class EgModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgModuleApplication.class, args);
    }

}
