package com.example.seataaccount.service.impl;

import com.example.seataaccount.entity.Account;
import com.example.seataaccount.repository.AccountRepository;
import com.example.seataaccount.service.AccountService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
