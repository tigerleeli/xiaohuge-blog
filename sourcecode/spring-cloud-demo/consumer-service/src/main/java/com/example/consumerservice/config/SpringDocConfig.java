package com.example.consumerservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("接口标题")
                .description("接口描述")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("小虎哥")
                        .email("邮箱")));
    }
}
