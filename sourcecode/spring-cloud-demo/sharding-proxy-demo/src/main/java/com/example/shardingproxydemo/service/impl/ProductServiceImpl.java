package com.example.shardingproxydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingproxydemo.entity.Product;
import com.example.shardingproxydemo.mapper.ProductMapper;
import com.example.shardingproxydemo.service.ProductService;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
