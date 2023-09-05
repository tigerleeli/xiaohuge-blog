package com.example.smsservice.sender;

public interface SmsSender {
    void sendSms(String phone, String message);
}
