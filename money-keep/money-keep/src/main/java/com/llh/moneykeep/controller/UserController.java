package com.llh.moneykeep.controller;

import com.llh.moneykeep.common.CommonResult;
import com.llh.moneykeep.common.ContextHolder;
import com.llh.moneykeep.dto.user.LoginParam;
import com.llh.moneykeep.dto.user.LoginResult;
import com.llh.moneykeep.dto.user.RegisterParam;
import com.llh.moneykeep.entity.User;
import com.llh.moneykeep.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    public CommonResult<LoginResult> login(@Validated
                                           @RequestBody LoginParam param) {
        log.info("用户登录： {}", param.getUsername());
        return CommonResult.success(userService.login(param));
    }

    @PostMapping("register")
    public CommonResult<Boolean> register(@Validated
                                          @RequestBody RegisterParam param) {
        log.info("用户注册： {}", param.getUsername());
        return CommonResult.success(userService.register(param));
    }

    @GetMapping("get")
    public CommonResult<User> get() {
        log.info("获取个人信息");
        return CommonResult.success(userService.getById(ContextHolder.getContext().getUserId()));
    }
}
