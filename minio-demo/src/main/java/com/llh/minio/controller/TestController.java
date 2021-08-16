package com.llh.minio.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;
import cn.hutool.system.SystemUtil;
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
import java.io.*;
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

    private static final String TEMP_DIR = "temp/";

    @PostMapping("upload")
    public String upload(@RequestParam(name = "file", required = false) MultipartFile multipartFile) {
        try {
            TokenizerEngine engine = new HanLPEngine();

            int index = Objects.requireNonNull(multipartFile.getOriginalFilename()).lastIndexOf(".");
            String suffix = multipartFile.getOriginalFilename().substring(index + 1);
            String fileName = UUID.randomUUID().toString() + "." + suffix;
            String path = "1/" + fileName;
            String fileType = FileTypeUtil.getType(multipartFile.getInputStream());
            if (fileType.equalsIgnoreCase(ImgUtil.IMAGE_TYPE_JPG) || fileType.equalsIgnoreCase(ImgUtil.IMAGE_TYPE_JPEG) || fileType.equalsIgnoreCase(ImgUtil.IMAGE_TYPE_PNG)) {
                ThreadUtil.execAsync(() -> {
                    try {

                        log.info("异步生成缩略图");
                        long startMilli = System.currentTimeMillis();
                        File tempFile = new File(TEMP_DIR + fileName);
                        File tempDir = new File(TEMP_DIR);
                        if (!tempDir.exists()) {
                            boolean res = tempDir.mkdir();
                            log.info("创建临时文件目录");
                        }
                        log.info("创建临时文件");
                        OutputStream tempOutStream = new FileOutputStream(tempFile);
                        ImgUtil.scale(multipartFile.getInputStream(), tempOutStream, 0.2f);
                        tempOutStream.close();

                        InputStream tempInputStream = new FileInputStream(tempFile);
                        String small = "1/small" + fileName;
                        minioClient.putObject(PutObjectArgs.builder()
                                .stream(tempInputStream, tempFile.length(), PutObjectArgs.MIN_MULTIPART_SIZE)
                                .object(small)
                                .contentType(multipartFile.getContentType())
                                .bucket(minioProperties.getBucket())
                                .build());
                        tempInputStream.close();
                        if (tempFile.exists() && tempFile.isFile()) {
                            boolean res = tempFile.delete();
                            log.info("删除临时文件 {}", res);
                        }
                        long endMilli = System.currentTimeMillis();
                        log.info("保存缩略图 花费时间：{}毫秒", endMilli - startMilli);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            long startMilli = System.currentTimeMillis();
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                    .object(path)
                    .contentType(multipartFile.getContentType())
                    .bucket(minioProperties.getBucket())
                    .build());
//            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
//                    .bucket(minioProperties.getBucket())
//                    .object(multipartFile.getOriginalFilename()).build());
            long endMilli = System.currentTimeMillis();
            log.info("保存文件 花费时间：{}毫秒", endMilli - startMilli);
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
