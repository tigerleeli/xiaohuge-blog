package com.example.seataaccount.service.impl;

import com.example.seataaccount.entity.Account;
import com.example.seataaccount.repository.AccountRepository;
import com.example.seataaccount.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;


    @Override
    public void decreaseMoney(Long userId, int money) {
        Account account = accountRepository.getById(userId);
        account.setMoney(account.getMoney() - money);
        accountRepository.save(account);
    }
}
