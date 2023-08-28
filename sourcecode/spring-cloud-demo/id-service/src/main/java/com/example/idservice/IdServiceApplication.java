package com.example.idservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.idservice.mapper")
public class IdServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdServiceApplication.class, args);
    }

}
