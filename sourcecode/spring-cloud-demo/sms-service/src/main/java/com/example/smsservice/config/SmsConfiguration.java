package com.example.smsservice.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfiguration {
    @Bean
    public IAcsClient iAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAjVUwKznS*****",
                "BNPO1zoNSi484oizGM9fzzwJJ*****");
        return new DefaultAcsClient(profile);
    }
}
