package com.example.redissondemo.controller;

import com.example.redissondemo.service.ProductOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductOrderService productOrderService;

    @GetMapping("buy")
    public Boolean buy() {
        return productOrderService.buy(1L, 1);
    }

    @GetMapping("buyWithoutLock")
    public Boolean buyWithoutLock() {
        return productOrderService.buyWithoutLock(1L, 1);
    }
}
