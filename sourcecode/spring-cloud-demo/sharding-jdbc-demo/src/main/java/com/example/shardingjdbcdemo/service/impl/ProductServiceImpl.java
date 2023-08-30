package com.example.shardingjdbcdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingjdbcdemo.entity.Company;
import com.example.shardingjdbcdemo.entity.Product;
import com.example.shardingjdbcdemo.mapper.CompanyMapper;
import com.example.shardingjdbcdemo.mapper.ProductMapper;
import com.example.shardingjdbcdemo.service.CompanyService;
import com.example.shardingjdbcdemo.service.ProductService;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
