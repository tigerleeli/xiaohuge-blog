package com.llh.product.controller;

import com.llh.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小虎哥的技术博客
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/decrease/{productId}/{number}")
    public Boolean decrease(@PathVariable Long productId, @PathVariable Integer number) {
        return productService.decrease(productId, number);
    }
}
