package com.example.seataorder.service.impl;

import com.example.seataorder.entity.Order;
import com.example.seataorder.remote.AccountService;
import com.example.seataorder.repository.OrderRepository;
import com.example.seataorder.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;

    @Resource
    private AccountService accountService;

    @Override
    public void createOrder(Long userId, Long productId, int number, int money) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPurchaseNumber(number);
        order.setPurchaseMoney(money);
        // 创建订单
        orderRepository.save(order);

        // 调用账户服务 扣除账户余额
        accountService.decreaseMoney(userId, money);
    }
}
