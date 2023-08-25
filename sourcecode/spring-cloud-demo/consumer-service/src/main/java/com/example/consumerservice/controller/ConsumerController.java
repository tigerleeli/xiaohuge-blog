package com.example.consumerservice.controller;

import com.example.consumerservice.api.ConsumerApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Resource
    private ConsumerApi consumerApi;

    @GetMapping("test")
    public String test(@RequestParam String name) {
        return consumerApi.test(name);
    }
}
