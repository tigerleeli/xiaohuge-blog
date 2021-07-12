package com.llh.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {

    private String endpoint = "http://localhost";

    private String accessKey = "minioadmin";

    private String secretKey = "minioadmin";

    private String bucket = "test";

}
