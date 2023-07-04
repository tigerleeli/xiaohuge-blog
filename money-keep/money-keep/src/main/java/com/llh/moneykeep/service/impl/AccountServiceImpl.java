package com.llh.moneykeep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.entity.Account;
import com.llh.moneykeep.mapper.AccountMapper;
import com.llh.moneykeep.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
