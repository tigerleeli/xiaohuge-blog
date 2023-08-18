package com.llh.minio.config;

import io.minio.MinioClient;
import org.simpleframework.xml.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {

    private static final String END_POINT = "http://localhost:9000";

    private static final String USERNAME = "minioadmin";

    private static final String PASSWORD = "minioadmin";

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(END_POINT)
                .credentials(USERNAME, PASSWORD)
                .build();
    }

    public static void main(String[] args) {
        System.out.println(OrderTypeEnum.PURCHASE.eq(2));
    }
}
