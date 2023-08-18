package com.llh.springbootpage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.springbootpage.entity.UserInfo;
import com.llh.springbootpage.mapper.UserInfoMapper;
import com.llh.springbootpage.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author llh
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


}
