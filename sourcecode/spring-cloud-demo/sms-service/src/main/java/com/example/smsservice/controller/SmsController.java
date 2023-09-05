package com.example.smsservice.controller;

import com.example.smsservice.service.SmsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("sms")
public class SmsController {
    @Resource
    private SmsService smsService;

    @GetMapping("sendSmsVerifyCode")
    public void sendSmsVerifyCode(@RequestParam String phone) {
        smsService.sendSmsVerifyCode(phone);
    }
}
