package com.example.shardingproxydemo.controller;


import com.example.shardingproxydemo.entity.Permission;
import com.example.shardingproxydemo.remote.IdService;
import com.example.shardingproxydemo.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @Resource
    private IdService idService;

    @GetMapping("add")
    public Boolean add() {
        Permission permission = new Permission();
        permission.setId(idService.generateId("permission"));
        permission.setName("权限名称：" + UUID.randomUUID());
        permission.setCreateTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        return permissionService.save(permission);
    }
}
