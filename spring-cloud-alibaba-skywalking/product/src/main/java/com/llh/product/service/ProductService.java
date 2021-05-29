package com.llh.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.product.api.entity.Product;

/**
 * @author 小虎哥的技术博客
 */
public interface ProductService extends IService<Product> {
    /**
     * 减少商品数量
     *
     * @param productId 商品id
     * @param number    数量
     * @return 是否成功
     */
    Boolean decrease(Long productId, Integer number);
}
