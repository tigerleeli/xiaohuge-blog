package com.example.demo.controller;

import com.example.demo.config.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("user")
public class UserController {

    @SysLog(name = "这是一个日志名称")
    @GetMapping("test")
    public String test() {
        return "hello world";
    }
}
