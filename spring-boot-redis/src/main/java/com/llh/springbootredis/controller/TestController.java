package com.llh.springbootredis.controller;

import com.llh.springbootredis.bean.UserInfo;
import com.llh.springbootredis.service.TestService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author llh
 */
@RestController
public class TestController {
    @Resource
    private TestService testService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("test0/{id}")
    public String test0(@PathVariable Long id) {
        // 查询缓存
        String cacheKey = "str:" + id;
        String cacheVal = stringRedisTemplate.opsForValue().get(cacheKey);
        if (cacheVal != null) {
            System.out.println("redis缓存中直接返回字符串");
            return cacheVal;
        }
        return testService.test0(id);
    }

    @GetMapping("test1/{id}")
    public UserInfo test1(@PathVariable Long id) {
        // 查询缓存
        String cacheKey = "user:" + id;
        UserInfo cacheVal = (UserInfo) redisTemplate.opsForValue().get(cacheKey);
        if (cacheVal != null) {
            System.out.println("redis缓存中直接返回对象");
            return cacheVal;
        }
        return testService.test1(id);
    }
}
