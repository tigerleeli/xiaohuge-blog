package com.llh.springbootdemo.controller;

import com.llh.springbootdemo.entity.UserInfo;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    /**
     * get请求 http://localhost:8080/test0
     * 不带参数
     */
    @GetMapping("/test0")
    public String test0() {
        return "hello world";
    }

    /**
     * get请求 http://localhost:8080/test1/admin
     * 带参数 插值的方式
     */
    @GetMapping("/test1/{name}")
    public String test1(@PathVariable("name") String name) {
        return name;
    }

    /**
     * get请求 http://localhost:8080/test2?name=admin
     * 带参数 传统方式
     */
    @GetMapping("/test2")
    public String test2(@RequestParam("name") String name) {
        return name;
    }

    /**
     * post请求 localhost:8080/test3
     * 参数为Json格式
     * {
     *     "username":"admin",
     *     "password":"123456"
     * }
     */
    @PostMapping("/test3")
    public String test3(@RequestBody UserInfo userInfo) {
        return userInfo.toString();
    }
}
