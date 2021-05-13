package com.llh.springbootdemo.controller;

import com.llh.springbootdemo.entity.UserInfo;
import com.llh.springbootdemo.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author llh
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public Boolean add(@RequestBody UserInfo userInfo) {
        return userInfoService.save(userInfo);
    }

    @GetMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userInfoService.removeById(id);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody UserInfo userInfo) {
        return userInfoService.updateById(userInfo);
    }

    @GetMapping("/get/{id}")
    public UserInfo get(@PathVariable Long id) {
        return userInfoService.getById(id);
    }

    @GetMapping("/list")
    public List<UserInfo> list() {
        return userInfoService.list();
    }

}
