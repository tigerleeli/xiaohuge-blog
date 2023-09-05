package com.example.smsservice.service;

public interface SmsService {
    /**
     * 发送短信验证码
     */
    void sendSmsVerifyCode(String phone, String message, String smsType);
}
