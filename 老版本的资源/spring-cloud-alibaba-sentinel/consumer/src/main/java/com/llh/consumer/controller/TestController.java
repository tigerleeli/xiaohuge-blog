package com.llh.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小虎哥的技术博客
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "test";
    }
}
