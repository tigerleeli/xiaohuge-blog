package com.llh.moneykeep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.moneykeep.dto.account.AccountAddParam;
import com.llh.moneykeep.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService extends IService<Account> {
    Boolean add(AccountAddParam param);

    Boolean removeById(Long id);

    List<Account> listAll();

    /**
     * 增加账户余额
     */
    Boolean increaseBalance(Long accountId, BigDecimal amount);

    /**
     * 减少账户余额
     */
    Boolean decreaseBalance(Long accountId, BigDecimal amount);
}
