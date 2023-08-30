package com.example.shardingjdbcdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingjdbcdemo.entity.Permission;
import com.example.shardingjdbcdemo.entity.Product;
import com.example.shardingjdbcdemo.mapper.PermissionMapper;
import com.example.shardingjdbcdemo.mapper.ProductMapper;
import com.example.shardingjdbcdemo.service.PermissionService;
import com.example.shardingjdbcdemo.service.ProductService;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
