package com.llh.moneykeep.controller;

import com.llh.moneykeep.common.CommonResult;
import com.llh.moneykeep.dto.category.CategoryAddParam;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("add")
    public CommonResult<Boolean> add(@Validated
                                     @RequestBody CategoryAddParam param) {
        log.info("添加分类： {}", param);
        return CommonResult.success(categoryService.add(param));
    }

    @GetMapping("removeById")
    public CommonResult<Boolean> removeById(@Validated
                                            @NotNull(message = "请选择记录")
                                            @RequestParam Long id) {
        log.info("删除分类： {}", id);
        return CommonResult.success(categoryService.removeById(id));
    }

    @GetMapping("listAll")
    public CommonResult<List<Category>> listAll(@Validated
                                                @NotNull(message = "请选择类型")
                                                @RequestParam Integer type) {
        log.info("获取所有分类");
        return CommonResult.success(categoryService.listAll(type));
    }
}
