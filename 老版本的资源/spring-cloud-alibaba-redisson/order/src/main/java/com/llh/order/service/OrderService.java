package com.llh.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.order.api.entity.ProductOrder;

/**
 * @author 小虎哥的技术博客
 */
public interface OrderService extends IService<ProductOrder> {
    /**
     * 创建订单
     *
     * @param productId 商品id
     * @param number    数量
     * @return 是否成功
     */
    Boolean create(Long productId, Integer number);
}
