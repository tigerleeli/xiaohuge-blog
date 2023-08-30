package com.example.redissondemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.redissondemo.entity.Product;
import com.example.redissondemo.entity.ProductOrder;
import com.example.redissondemo.mapper.ProductOrderMapper;
import com.example.redissondemo.service.ProductOrderService;
import com.example.redissondemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("productOrderService")
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements ProductOrderService {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ProductService productService;

    @Override
    public Boolean buy(Long productId, Integer number) {
        RLock lock = redissonClient.getLock("lock_key_" + productId);

        try {
            boolean lockRes = lock.tryLock(5, TimeUnit.SECONDS);
            if (!lockRes) {
                throw new RuntimeException("获取锁失败~");
            }

            Product product = productService.getById(productId);
            log.info("库存数量：{}", product.getNumber());
            // 判断库存是否充足
            if (product.getNumber() < number) {
                throw new RuntimeException("库存不足");
            }

            // 创建订单
            ProductOrder order = new ProductOrder();
            order.setProductId(productId);
            order.setNumber(1);
            order.setCreateTime(LocalDateTime.now());
            save(order);
            log.info("创建订单：{}", order);

            // 减库存
            product.setNumber(product.getNumber() - 1);
            productService.updateById(product);
            log.info("减库存：{}", product);
        } catch (InterruptedException e) {
            throw new RuntimeException("出现异常啦~");
        } finally {
            // 释放锁
            lock.unlock();
        }

        return true;
    }

    @Override
    public Boolean buyWithoutLock(Long productId, Integer number) {
        Product product = productService.getById(productId);
        log.info("库存数量：{}", product.getNumber());
        // 判断库存是否充足
        if (product.getNumber() < number) {
            throw new RuntimeException("库存不足");
        }

        // 创建订单
        ProductOrder order = new ProductOrder();
        order.setProductId(productId);
        order.setNumber(1);
        order.setCreateTime(LocalDateTime.now());
        save(order);
        log.info("创建订单：{}", order);

        // 减库存
        product.setNumber(product.getNumber() - 1);
        productService.updateById(product);
        log.info("减库存：{}", product);
        return true;
    }
}
