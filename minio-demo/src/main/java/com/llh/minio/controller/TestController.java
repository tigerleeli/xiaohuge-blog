package com.llh.minio.controller;

import com.llh.minio.config.MinioProperties;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author llh
 */
@RestController
@Slf4j
public class TestController {
    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioProperties minioProperties;

    @PostMapping("upload")
    public String upload(@RequestParam(name = "file", required = false) MultipartFile multipartFile) {
        try {
            int index = Objects.requireNonNull(multipartFile.getOriginalFilename()).lastIndexOf(".");
            String suffix = multipartFile.getOriginalFilename().substring(index);
            String path = UUID.randomUUID().toString() + suffix;

            minioClient.putObject(PutObjectArgs.builder()
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                    .object(path)
                    .contentType(multipartFile.getContentType())
                    .bucket(minioProperties.getBucket())
                    .build());
//            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
//                    .bucket(minioProperties.getBucket())
//                    .object(multipartFile.getOriginalFilename()).build());

            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(minioProperties.getBucket()).object(path).
                            method(Method.GET).expiry(7, TimeUnit.DAYS).build());
            log.info(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
