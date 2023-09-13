package com.example.providerservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("provider")
public class ProviderController {
    @GetMapping("test")
    public String test(@RequestParam String name) {
        System.out.println(name);
        return "hello " + name;
    }
}
