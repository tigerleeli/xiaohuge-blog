package com.llh.springbootpage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llh.springbootpage.config.CommonResult;
import com.llh.springbootpage.entity.UserInfo;
import com.llh.springbootpage.param.UserInfoPageParam;
import com.llh.springbootpage.result.BasePageResult;
import com.llh.springbootpage.service.UserInfoService;
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

    @PostMapping("/page")
    public CommonResult<BasePageResult<UserInfo>> page(@RequestBody UserInfoPageParam param) {
        IPage<UserInfo> pageParam = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<UserInfo> pageResult = userInfoService.page(pageParam, new LambdaQueryWrapper<UserInfo>()
                .like(UserInfo::getUsername, param.getKeyword()));
        return CommonResult.success(BasePageResult.newInstance(pageResult));
    }
}
