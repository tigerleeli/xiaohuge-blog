package com.example.configdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("config")
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String dataUrl;

    @GetMapping("test")
    public String test() {
        System.out.println(dataUrl);
        return dataUrl;
    }
}
