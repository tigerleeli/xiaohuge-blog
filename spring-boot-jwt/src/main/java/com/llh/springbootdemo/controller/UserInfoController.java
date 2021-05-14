package com.llh.springbootdemo.controller;

import com.llh.springbootdemo.config.CommonResult;
import com.llh.springbootdemo.entity.UserInfo;
import com.llh.springbootdemo.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author llh
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;


    @PostMapping("/register")
    public CommonResult<Boolean> register(@RequestBody UserInfo userInfo) {
        return CommonResult.success(userInfoService.register(userInfo));
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UserInfo userInfo) {
        return CommonResult.success(userInfoService.login(userInfo));
    }

    @PostMapping("/changePassword")
    public CommonResult<Boolean> changePassword(@RequestBody UserInfo userInfo) {
        return CommonResult.success(userInfoService.changePassword(userInfo));
    }

}
