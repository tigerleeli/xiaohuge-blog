package com.example.knife4juser.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("test")
    public String test(@RequestParam String name) {
        System.out.println(name);
        return "hello " + name;
    }
}
