package com.llh.moneykeep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.moneykeep.dto.user.LoginParam;
import com.llh.moneykeep.dto.user.LoginResult;
import com.llh.moneykeep.dto.user.RegisterParam;
import com.llh.moneykeep.entity.User;

public interface UserService extends IService<User> {

    LoginResult login(LoginParam param);

    Boolean register(RegisterParam param);
}
