package com.example.seataproduct.service;


public interface ProductService {

    /**
     * 减少库存数量
     */
    void decreaseNumber(Long id, int number);

}
