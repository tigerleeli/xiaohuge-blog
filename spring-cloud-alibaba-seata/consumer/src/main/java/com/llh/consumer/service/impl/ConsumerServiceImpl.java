package com.llh.consumer.service.impl;

import com.llh.consumer.service.ConsumerService;
import com.llh.order.api.feign.OrderApi;
import com.llh.product.api.feign.ProductApi;
import io.seata.spring.annotation.GlobalTransactional;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 小虎哥的技术博客
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Resource
    private RedissonClient redissonClient;

    @Resource
    private ProductApi productApi;

    @Resource
    private OrderApi orderApi;

    @Override
    @GlobalTransactional
    public Boolean buy(Long productId, Integer number) {
        RLock lock = redissonClient.getLock("lock_key");
        // 加锁
        lock.lock();
        // 先减商品库存
        boolean decreaseResult = productApi.decrease(productId, number);
        if (decreaseResult) {
            // 商品库存减成功后 创建订单
            boolean createResult = orderApi.create(productId, number);
            // 解锁
            lock.unlock();
            return createResult;
        }
        return false;
    }
}
