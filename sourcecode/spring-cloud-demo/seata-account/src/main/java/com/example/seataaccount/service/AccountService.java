package com.example.seataaccount.service;


public interface AccountService {

    /**
     * 减少用户余额
     */
    void decreaseMoney(Long userId, int money);

}
