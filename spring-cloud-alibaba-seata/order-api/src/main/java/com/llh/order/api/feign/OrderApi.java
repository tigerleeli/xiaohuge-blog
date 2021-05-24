package com.llh.order.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 小虎哥的技术博客
 */
@FeignClient(name = "order")
public interface OrderApi {
    /**
     * 创建订单
     *
     * @param productId 商品id
     * @param number    数量
     * @return 是否成功
     */
    @GetMapping("/order/create/{productId}/{number}")
    Boolean create(@PathVariable Long productId, @PathVariable Integer number);
}
