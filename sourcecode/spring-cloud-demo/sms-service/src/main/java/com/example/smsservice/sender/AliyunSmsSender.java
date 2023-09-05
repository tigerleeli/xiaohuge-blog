package com.example.smsservice.sender;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class AliyunSmsSender implements SmsSender {
    @Resource
    private IAcsClient client;

    @Override
    public void sendSms(String phone, String message) {
        // 发送阿里云短信服务
        // 您的验证码为：${code}，请勿泄露于他人！
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("sign_name"); // 签名名称
        request.setTemplateCode("SMS_xxxxxxxxx"); // 短信模板
        //  message = "{\"code\":\"1234\"}";
        request.setTemplateParam(message);
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            log.info(response.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            // 打印错误码
            log.info("ErrCode:{}", e.getErrCode());
            log.info("ErrMsg:{}", e.getErrMsg());
            log.info("RequestId:{}", e.getRequestId());
        }
    }
}
