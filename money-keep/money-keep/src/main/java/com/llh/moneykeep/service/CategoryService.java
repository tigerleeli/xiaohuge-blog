package com.llh.moneykeep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.moneykeep.dto.account.AccountAddParam;
import com.llh.moneykeep.dto.category.CategoryAddParam;
import com.llh.moneykeep.entity.Account;
import com.llh.moneykeep.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    Boolean add(CategoryAddParam param);

    Boolean removeById(Long id);

    List<Category> listAll(Integer type);
}
