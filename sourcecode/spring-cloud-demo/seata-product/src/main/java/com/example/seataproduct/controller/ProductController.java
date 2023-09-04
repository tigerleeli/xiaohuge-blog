package com.example.seataproduct.controller;

import com.example.seataproduct.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("decreaseNumber")
    public void decreaseNumber(@RequestParam Long id,
                              @RequestParam int number) {
        productService.decreaseNumber(id, number);
    }
}
