package com.llh.moneykeep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.llh.moneykeep.mapper")
@SpringBootApplication
public class MoneyKeepApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyKeepApplication.class, args);
    }

}
