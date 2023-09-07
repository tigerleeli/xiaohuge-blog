package com.example.shardingproxydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingproxydemo.entity.Permission;
import com.example.shardingproxydemo.mapper.PermissionMapper;
import com.example.shardingproxydemo.service.PermissionService;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
