package com.example.smsservice.service.impl;

import com.example.smsservice.sender.SmsFactory;
import com.example.smsservice.sender.SmsSender;
import com.example.smsservice.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsFactory smsFactory;

    @Override
    public void sendSmsVerifyCode(String phone) {
        SmsSender sender = smsFactory.createSmsSender("aliyun");
        sender.sendSms(phone, "{\"code\":\"1234\"}");
    }
}
