package com.llh.moneykeep.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.common.ContextHolder;
import com.llh.moneykeep.dto.account.AccountAddParam;
import com.llh.moneykeep.entity.Account;
import com.llh.moneykeep.mapper.AccountMapper;
import com.llh.moneykeep.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public Boolean add(AccountAddParam param) {
        long userId = ContextHolder.getContext().getUserId();
        Account account = new Account();
        account.setName(param.getName());
        account.setBalance(BigDecimal.ZERO);
        account.setUserId(userId);
        return save(account);
    }

    @Override
    public Boolean removeById(Long id) {
        long userId = ContextHolder.getContext().getUserId();
        Account account = new Account();
        // 逻辑删除
        account.setIsDeleted(1);
        // 不用updateById(account)是为了防止恶意删除
        return update(account, new LambdaQueryWrapper<Account>()
                .eq(Account::getId, id)
                .eq(Account::getUserId, userId));
    }

    @Override
    public List<Account> listAll() {
        long userId = ContextHolder.getContext().getUserId();
        return list(new LambdaQueryWrapper<Account>()
                .eq(Account::getUserId, userId)
                .eq(Account::getIsDeleted, 0));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean increaseBalance(Long accountId, BigDecimal amount) {
        long userId = ContextHolder.getContext().getUserId();
        Account selected = getById(accountId);
        Account update = new Account();
        update.setBalance(selected.getBalance().add(amount));
        return update(update, new LambdaQueryWrapper<Account>()
                .eq(Account::getId, accountId)
                .eq(Account::getUserId, userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean decreaseBalance(Long accountId, BigDecimal amount) {
        return increaseBalance(accountId, amount.negate());
    }
}
