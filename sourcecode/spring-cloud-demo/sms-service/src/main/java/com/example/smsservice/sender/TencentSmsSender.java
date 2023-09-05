package com.example.smsservice.sender;

import org.springframework.stereotype.Component;

@Component
public class TencentSmsSender implements SmsSender {
    @Override
    public void sendSms(String phone, String message) {
        // 发送腾讯云短信服务
        // ...
    }
}
