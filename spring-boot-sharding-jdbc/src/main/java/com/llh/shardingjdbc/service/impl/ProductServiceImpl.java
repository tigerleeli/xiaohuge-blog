package com.llh.shardingjdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.shardingjdbc.entity.Product;
import com.llh.shardingjdbc.mapper.ProductMapper;
import com.llh.shardingjdbc.service.ProductService;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
