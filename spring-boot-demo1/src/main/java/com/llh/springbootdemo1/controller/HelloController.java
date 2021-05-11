package com.llh.springbootdemo1.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/test0")
    public String test0() {
        return "hello world";
    }

    @GetMapping("/test1/{name}")
    public String test1(@PathVariable("name") String name) {
        return name;
    }

    @GetMapping("/test2")
    public String test2(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping("/test3")
    public String test3(@RequestBody UserInfo userInfo) {
        return userInfo.toString();
    }
}
