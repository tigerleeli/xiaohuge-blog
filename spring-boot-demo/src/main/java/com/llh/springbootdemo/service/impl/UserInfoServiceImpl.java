package com.llh.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.springbootdemo.entity.UserInfo;
import com.llh.springbootdemo.mapper.UserInfoMapper;
import com.llh.springbootdemo.service.UserInfoService;

public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
