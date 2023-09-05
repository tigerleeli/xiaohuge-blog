package com.example.smsservice.factory;

import com.example.smsservice.sender.SmsSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SmsFactory {
    @Resource
    private SmsSender aliyunSmsSender;

    @Resource
    private SmsSender tencentSmsSender;

    public SmsSender createSmsSender(String type) {
        if ("aliyun".equalsIgnoreCase(type)) {
            return aliyunSmsSender;
        } else if ("tencent".equalsIgnoreCase(type)) {
            return tencentSmsSender;
        } else {
            throw new IllegalArgumentException("Unknown sms type: " + type);
        }
    }
}
