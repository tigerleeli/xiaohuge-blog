package com.llh.consumer.service.impl;

import com.llh.consumer.service.ConsumerService;
import com.llh.product.api.feign.ProductApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 小虎哥的技术博客
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Resource
    private ProductApi productApi;

    @Override
    public Boolean buy(Long productId, Integer number) {
        productApi.decrease(productId, number);
        return true;
    }
}
