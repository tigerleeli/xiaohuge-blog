package com.llh.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.product.api.entity.Product;
import com.llh.product.mapper.ProductMapper;
import com.llh.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author 小虎哥的技术博客
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public Boolean decrease(Long productId, Integer number) {
        Product product = getById(productId);
        // 判断商品数量够不够
        if ((product.getNumber() - number) >= 0) {
            // 商品数量减少
            product.setNumber(product.getNumber() - number);
            return updateById(product);
        } else {
            return false;
        }
    }
}
