package com.llh.moneykeep.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.common.BizException;
import com.llh.moneykeep.common.JwtUtil;
import com.llh.moneykeep.dto.user.LoginParam;
import com.llh.moneykeep.dto.user.LoginResult;
import com.llh.moneykeep.dto.user.RegisterParam;
import com.llh.moneykeep.entity.User;
import com.llh.moneykeep.mapper.UserMapper;
import com.llh.moneykeep.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public LoginResult login(LoginParam param) {
        // 查询用户是否注册
        List<User> userList = list(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, param.getUsername())
                .eq(User::getIsDeleted, 0));
        if (CollUtil.isEmpty(userList)) {
            throw new BizException("用户名未注册");
        }
        User user = userList.get(0);

        // 验证密码是否正确
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPassword = passwordEncoder.matches(param.getPassword(), user.getPassword());
        if (!isPassword) {
            throw new BizException("密码错误");
        }

        // 生成令牌
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", String.valueOf(user.getId()));
        String token = JwtUtil.generateToken(map);
        return new LoginResult(token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(RegisterParam param) {
        // 查询用户名是否已注册
        List<User> userList = list(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, param.getUsername())
                .eq(User::getIsDeleted, 0));
        if (CollUtil.isNotEmpty(userList)) {
            throw new BizException("该用户名已注册");
        }

        User user = new User();
        user.setUsername(param.getUsername());
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(param.getPassword());
        user.setPassword(encodedPassword);
        return save(user);
    }

}
