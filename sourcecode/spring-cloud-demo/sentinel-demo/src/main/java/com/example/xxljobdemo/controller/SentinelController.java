package com.example.xxljobdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("sentinel")
public class SentinelController {
    @GetMapping("test")
    public String test() {
        System.out.println(LocalDateTime.now());
        return "hello world";
    }
}
