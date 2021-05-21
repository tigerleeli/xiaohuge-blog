package com.llh.product.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 小虎哥的技术博客
 */
@FeignClient(name = "product")
public interface ProductApi {
    /**
     * 减少商品数量
     *
     * @param productId 商品id
     * @param number    数量
     * @return 是否成功
     */
    @GetMapping("/product/decrease/{productId}/{number}")
    Boolean decrease(@PathVariable Long productId, @PathVariable Integer number);
}
