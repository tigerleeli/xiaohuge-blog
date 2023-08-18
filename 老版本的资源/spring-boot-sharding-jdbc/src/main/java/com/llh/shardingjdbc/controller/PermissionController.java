package com.llh.shardingjdbc.controller;

import com.llh.shardingjdbc.entity.Permission;
import com.llh.shardingjdbc.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 权限相关接口
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @PostMapping("add")
    public Boolean add(@RequestBody Permission permission) {
        permission.setCreateTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        return permissionService.save(permission);
    }

    @GetMapping("get")
    public Permission get(@RequestParam Long id) {
        return permissionService.getById(id);
    }
}
