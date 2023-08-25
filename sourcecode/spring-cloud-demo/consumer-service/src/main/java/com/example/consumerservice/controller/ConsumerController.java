package com.example.consumerservice.controller;

import com.example.consumerservice.remote.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Resource
    private ProviderService providerService;

    @GetMapping("test")
    public String test(@RequestParam String name) {
        System.out.println(name);
        return providerService.test(name);
    }
}