package com.example.seataproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@SpringBootApplication
public class SeataProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataProductApplication.class, args);
	}

}
