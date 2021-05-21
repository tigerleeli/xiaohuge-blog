package com.llh.consumer.service.impl;

import com.llh.consumer.service.ConsumerService;
import com.llh.order.api.feign.OrderApi;
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

    @Resource
    private OrderApi orderApi;

    @Override
    public Boolean buy(Long productId, Integer number) {
        // 第一步：减商品库存数量
        productApi.decrease(productId, number);

        // 第二步：创建订单
        orderApi.create(productId, number);
        return true;
    }
}
