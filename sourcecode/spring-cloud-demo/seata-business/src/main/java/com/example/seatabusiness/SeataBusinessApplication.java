package com.example.seatabusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@SpringBootApplication
public class SeataBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataBusinessApplication.class, args);
    }

}
