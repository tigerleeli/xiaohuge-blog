package com.llh.shardingjdbc.controller;

import com.llh.shardingjdbc.entity.Product;
import com.llh.shardingjdbc.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@RestController
@RequestMapping("product")
public class ProductController {
    @Resource
    private ProductService productService;

    @PostMapping("add")
    public Boolean add(@RequestBody Product product) {
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return productService.save(product);
    }

    @GetMapping("get")
    public Product get(@RequestParam Long id) {
        return productService.getById(id);
    }
}
