package com.example.redissondemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.redissondemo.entity.ProductOrder;

public interface ProductOrderService extends IService<ProductOrder> {
    Boolean buy(Long productId, Integer number);

    Boolean buyWithoutLock(Long productId, Integer number);
}
