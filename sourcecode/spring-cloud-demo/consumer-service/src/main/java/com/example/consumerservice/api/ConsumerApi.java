package com.example.consumerservice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider-service")
public interface ConsumerApi {

    @GetMapping("/provider/test")
    String test(@RequestParam String name);
}
