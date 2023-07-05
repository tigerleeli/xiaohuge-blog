package com.llh.moneykeep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.common.ContextHolder;
import com.llh.moneykeep.dto.category.CategoryAddParam;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.mapper.CategoryMapper;
import com.llh.moneykeep.service.CategoryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Boolean add(CategoryAddParam param) {
        long userId = ContextHolder.getContext().getUserId();
        Category category = new Category();
        category.setName(param.getName());
        category.setType(param.getType());
        category.setUserId(userId);
        return save(category);
    }

    @Override
    public Boolean removeById(Long id) {
        long userId = ContextHolder.getContext().getUserId();
        Category category = new Category();
        // 逻辑删除
        category.setIsDeleted(1);
        // 不用updateById(category)是为了防止恶意删除
        return update(category, new LambdaQueryWrapper<Category>()
                .eq(Category::getId, id)
                .eq(Category::getUserId, userId));
    }

    @Override
    public List<Category> listAll() {
        long userId = ContextHolder.getContext().getUserId();
        return list(new LambdaQueryWrapper<Category>()
                .eq(Category::getUserId, userId)
                .eq(Category::getIsDeleted, 0));
    }
}
