package com.llh.consumer.controller;

import com.llh.consumer.service.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小虎哥的技术博客
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Resource
    private ConsumerService consumerService;

    @GetMapping("/buy/{productId}/{number}")
    public Boolean buy(@PathVariable Long productId, @PathVariable Integer number) {
        System.out.println("/consumer/buy/" + productId + "/" + number);
        return consumerService.buy(productId, number);
    }
}
