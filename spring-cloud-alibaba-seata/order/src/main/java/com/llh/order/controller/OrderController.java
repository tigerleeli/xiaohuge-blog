package com.llh.order.controller;

import com.llh.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小虎哥的技术博客
 */
@RestController
@RequestMapping("/product")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create/{productId}/{number}")
    public Boolean create(@PathVariable Long productId, @PathVariable Integer number) {
        return orderService.create(productId, number);
    }
}
