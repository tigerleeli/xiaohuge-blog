package com.llh.springbootredis.service.impl;

import com.llh.springbootredis.bean.UserInfo;
import com.llh.springbootredis.service.TestService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author llh
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String test0(Long id) {
        System.out.println("产生一个新字符串");
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();

        String cacheKey = "str:" + id;
        System.out.println("redis缓存此字符串 " + cacheKey + " ===> " + str);
        stringRedisTemplate.opsForValue().set(cacheKey, str);
        return str;
    }

    @Override
    public UserInfo test1(Long id) {
        System.out.println("产生一个新对象");
        UserInfo userInfo = new UserInfo(id, "admin", "123456");

        String cacheKey = "user:" + id;
        System.out.println("redis缓存此对象 " + cacheKey + " ===> " + userInfo);
        redisTemplate.opsForValue().set(cacheKey, userInfo);
        return userInfo;
    }
}
