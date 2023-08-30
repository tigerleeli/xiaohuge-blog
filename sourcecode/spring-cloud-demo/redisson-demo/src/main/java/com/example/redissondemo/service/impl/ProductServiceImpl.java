package com.example.redissondemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redissondemo.entity.Product;
import com.example.redissondemo.mapper.ProductMapper;
import com.example.redissondemo.service.ProductService;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
