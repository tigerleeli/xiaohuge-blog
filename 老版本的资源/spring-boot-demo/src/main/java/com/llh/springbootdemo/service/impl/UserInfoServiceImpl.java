package com.llh.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.springbootdemo.config.ContextHolder;
import com.llh.springbootdemo.entity.UserInfo;
import com.llh.springbootdemo.mapper.UserInfoMapper;
import com.llh.springbootdemo.service.UserInfoService;
import com.llh.springbootdemo.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author llh
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public Boolean register(UserInfo userInfo) {
        List<UserInfo> selectedList = list(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUsername, userInfo.getUsername()));
        if (!selectedList.isEmpty()) {
            throw new RuntimeException("注册失败，该用户名已存在");
        }
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encodedPassword);
        return save(userInfo);
    }

    @Override
    public String login(UserInfo userInfo) {
        List<UserInfo> selectedList = list(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUsername, userInfo.getUsername()));
        if (selectedList.isEmpty()) {
            throw new RuntimeException("登录失败，账号不存在");
        }
        UserInfo selected = selectedList.get(0);
        String encodedPassword = selected.getPassword();
        // 判断密码是否正确
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(userInfo.getPassword(), encodedPassword);
        if (!result) {
            throw new RuntimeException("登录失败，用户密码错误");
        }
        // 生成令牌
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("userId", selected.getId());
        String token = JwtUtil.generateToken(map);
        return token;
    }

    @Override
    public Boolean changePassword(UserInfo userInfo) {
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());

        UserInfo updateUserInfo = new UserInfo();
        updateUserInfo.setPassword(encodedPassword);
        // 从上下文对象里面获取用户id，而不是用户传过来的
        Long userId = ContextHolder.getUserId();
        updateUserInfo.setId(userId);
        return updateById(updateUserInfo);
    }

}
