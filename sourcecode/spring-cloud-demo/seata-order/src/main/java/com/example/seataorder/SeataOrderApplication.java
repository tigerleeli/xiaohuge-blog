package com.example.seataorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@SpringBootApplication
public class SeataOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataOrderApplication.class, args);
	}

}
