package com.example.seatabusiness.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seata-product")
public interface ProductService {

    @GetMapping("/product/decreaseNumber")
     void decreaseNumber(@RequestParam Long id,
                               @RequestParam int number);
}
