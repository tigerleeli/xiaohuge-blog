package com.example.seataorder.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seata-account")
public interface AccountService {

    @GetMapping("/account/decreaseMoney")
    void decreaseMoney(@RequestParam Long userId,
                       @RequestParam int money);
}
