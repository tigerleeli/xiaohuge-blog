package com.example.providerservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域的域名 *表示允许所有域名
        config.addAllowedOriginPattern("*");
        // 设置允许跨域请求的方法 *表示允许所有方法
        config.addAllowedMethod("*");
        // 设置允许跨域请求头 *表示允许所有头部信息
        config.addAllowedHeader("*");
        // 是否发送 Cookie
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
