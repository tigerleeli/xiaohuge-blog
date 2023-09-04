package com.example.seataaccount.controller;

import com.example.seataaccount.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("decreaseMoney")
    public void decreaseMoney(@RequestParam Long userId,
                              @RequestParam int money) {
        accountService.decreaseMoney(userId, money);
    }
}
