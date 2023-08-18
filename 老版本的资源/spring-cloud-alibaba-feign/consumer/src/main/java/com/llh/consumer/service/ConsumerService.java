package com.llh.consumer.service;

/**
 * @author 小虎哥的技术博客
 */
public interface ConsumerService {
    /**
     * 减少商品数量
     *
     * @param productId 商品id
     * @param number    数量
     * @return 是否成功
     */
    Boolean buy(Long productId, Integer number);
}
