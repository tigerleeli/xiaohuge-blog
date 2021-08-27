package com.llh.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llh.shardingjdbc.entity.Product;
import org.springframework.stereotype.Service;


@Service
public interface ProductMapper extends BaseMapper<Product> {
}
