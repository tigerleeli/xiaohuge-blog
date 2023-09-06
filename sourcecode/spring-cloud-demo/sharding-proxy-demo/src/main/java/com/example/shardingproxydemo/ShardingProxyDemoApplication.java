package com.example.shardingproxydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@MapperScan(value = "com.example.shardingproxydemo.mapper")
@SpringBootApplication
public class ShardingProxyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingProxyDemoApplication.class, args);
    }

}
