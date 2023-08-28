package com.example.providerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider")
public class ProviderController {
    @GetMapping("test")
    public String test(@RequestParam String name) {
        System.out.println(name);
        return "hello " + name;
    }
}
