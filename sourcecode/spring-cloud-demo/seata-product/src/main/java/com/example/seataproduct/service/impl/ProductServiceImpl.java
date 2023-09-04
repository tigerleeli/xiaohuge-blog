package com.example.seataproduct.service.impl;

import com.example.seataproduct.entity.Product;
import com.example.seataproduct.repository.ProductRepository;
import com.example.seataproduct.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public void decreaseNumber(Long id, int number) {
        Product product = productRepository.getById(id);
        product.setProductNumber(product.getProductNumber() - number);
        productRepository.save(product);
    }
}
