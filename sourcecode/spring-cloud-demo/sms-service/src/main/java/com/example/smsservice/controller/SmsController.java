package controller;

import com.example.smsservice.service.SmsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
