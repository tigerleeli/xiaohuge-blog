package com.llh.order.service.impl;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.order.api.entity.ProductOrder;
import com.llh.order.mapper.OrderMapper;
import com.llh.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author 小虎哥的技术博客
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, ProductOrder> implements OrderService {
    @Override
    public Boolean create(Long productId, Integer number) {
        ProductOrder productOrder = new ProductOrder();

        // 生成唯一订单id
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        productOrder.setId(id);
        productOrder.setProductId(productId);
        productOrder.setNumber(number);
        productOrder.setCreateTime(LocalDateTime.now());
        return save(productOrder);
    }
}
