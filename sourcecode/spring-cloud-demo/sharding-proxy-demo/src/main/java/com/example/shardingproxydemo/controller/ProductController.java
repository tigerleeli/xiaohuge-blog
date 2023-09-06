package com.example.shardingproxydemo.controller;


import com.example.shardingproxydemo.entity.Product;
import com.example.shardingproxydemo.remote.IdService;
import com.example.shardingproxydemo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private IdService idService;

    @GetMapping("add")
    public Boolean add(@RequestParam Long companyId) {
        Product product = new Product();
        product.setId(idService.generateId("product"));
        product.setCompanyId(companyId);
        product.setName("商品名称：" + UUID.randomUUID());
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return productService.save(product);
    }
}
