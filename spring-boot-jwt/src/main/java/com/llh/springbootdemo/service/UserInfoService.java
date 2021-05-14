package com.llh.springbootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.springbootdemo.entity.UserInfo;

/**
 * @author llh
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 注册
     *
     * @param userInfo 注册信息
     * @return 是否成功
     */
    Boolean register(UserInfo userInfo);

    /**
     * 登录
     *
     * @param userInfo 登录信息
     * @return 令牌
     */
    String login(UserInfo userInfo);

    /**
     * 更改密码
     *
     * @param userInfo 用户信息
     * @return 是否成功
     */
    Boolean changePassword(UserInfo userInfo);
}
