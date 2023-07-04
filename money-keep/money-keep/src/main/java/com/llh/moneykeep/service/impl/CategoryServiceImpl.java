package com.llh.moneykeep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.mapper.CategoryMapper;
import com.llh.moneykeep.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
