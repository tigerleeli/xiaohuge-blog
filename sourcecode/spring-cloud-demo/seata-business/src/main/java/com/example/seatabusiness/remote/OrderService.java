package com.example.seatabusiness.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seata-order")
public interface OrderService {

    @GetMapping("/order/createOrder")
    void createOrder(@RequestParam Long userId,
                     @RequestParam Long productId,
                     @RequestParam int number,
                     @RequestParam int money);
}
