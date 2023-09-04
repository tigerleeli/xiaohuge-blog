package com.example.seatabusiness.service.impl;

import com.example.seatabusiness.remote.OrderService;
import com.example.seatabusiness.remote.ProductService;
import com.example.seatabusiness.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private ProductService productService;

    @Resource
    private OrderService orderService;

    @GlobalTransactional
    @Override
    public void purchase() {
        // 调用订单服务创建订单
        orderService.createOrder(1L, 1L, 1, 1499);

        // 调用商品服务扣除库存
        productService.decreaseNumber(1L, 1);
    }
}
