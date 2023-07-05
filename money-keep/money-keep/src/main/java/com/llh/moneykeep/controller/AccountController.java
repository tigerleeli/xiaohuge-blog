package com.llh.moneykeep.controller;

import com.llh.moneykeep.common.CommonResult;
import com.llh.moneykeep.dto.account.AccountAddParam;
import com.llh.moneykeep.entity.Account;
import com.llh.moneykeep.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("add")
    public CommonResult<Boolean> add(@Validated
                                     @RequestBody AccountAddParam param) {
        log.info("添加账户： {}", param);
        return CommonResult.success(accountService.add(param));
    }

    @GetMapping("removeById")
    public CommonResult<Boolean> removeById(@Validated
                                            @NotNull(message = "请选择记录")
                                            @RequestParam Long id) {
        log.info("删除账户： {}", id);
        return CommonResult.success(accountService.removeById(id));
    }

    @GetMapping("listAll")
    public CommonResult<List<Account>> listAll() {
        log.info("获取所有账户");
        return CommonResult.success(accountService.listAll());
    }
}
