package com.example.seataorder.controller;

import com.example.seataorder.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("createOrder")
    public void createOrder(@RequestParam Long userId,
                            @RequestParam Long productId,
                            @RequestParam int number,
                            @RequestParam int money) {
        orderService.createOrder(userId, productId, number, money);
    }
}
