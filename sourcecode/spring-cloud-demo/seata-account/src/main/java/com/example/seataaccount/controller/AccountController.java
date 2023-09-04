package com.example.seataaccount.controller;

import com.example.seataaccount.entity.Account;
import com.example.seataaccount.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("findAll")
    public List<Account> findAll() {
        return accountService.findAll();
    }
}
