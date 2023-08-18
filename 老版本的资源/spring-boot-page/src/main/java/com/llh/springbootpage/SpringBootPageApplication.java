package com.llh.springbootpage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author llh
 */
@SpringBootApplication
@MapperScan(value = "com.llh.springbootpage.mapper")
public class SpringBootPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPageApplication.class, args);
    }

}
