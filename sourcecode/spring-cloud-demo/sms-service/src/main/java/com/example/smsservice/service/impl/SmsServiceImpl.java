package com.example.smsservice.service.impl;

import com.example.smsservice.factory.SmsFactory;
import com.example.smsservice.sender.SmsSender;
import com.example.smsservice.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SmsServiceImpl implements SmsService {
    private static final String DEFAULT_SMS_TYPE = "aliyun"; // 默认使用阿里云短信服务

    @Resource
    private SmsFactory smsFactory;

    @Override
    public void sendSmsVerifyCode(String phone, String message, String smsType) {
        SmsSender sender = smsFactory.createSmsSender(smsType != null ? smsType : DEFAULT_SMS_TYPE);
        sender.sendSms(phone, message);
    }
}
