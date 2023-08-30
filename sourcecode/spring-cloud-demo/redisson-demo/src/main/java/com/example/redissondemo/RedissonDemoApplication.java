package com.example.redissondemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.example.redissondemo.mapper")
@SpringBootApplication
public class RedissonDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedissonDemoApplication.class, args);
    }

}
