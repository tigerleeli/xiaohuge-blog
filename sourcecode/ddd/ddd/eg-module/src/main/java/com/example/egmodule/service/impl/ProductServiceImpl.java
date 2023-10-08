package com.example.egmodule.service.impl;

import com.example.egmodule.entity.Product;
import com.example.egmodule.repository.ProductRepository;
import com.example.egmodule.service.ProductService;
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
