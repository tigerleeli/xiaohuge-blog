package com.llh.moneykeep.controller;

import com.llh.moneykeep.common.CommonResult;
import com.llh.moneykeep.dto.record.RecordAddParam;
import com.llh.moneykeep.dto.record.RecordPageParam;
import com.llh.moneykeep.dto.record.RecordPageResult;
import com.llh.moneykeep.dto.record.RecordResult;
import com.llh.moneykeep.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("record")
public class RecordController {

    @Resource
    private RecordService recordService;

    @PostMapping("add")
    public CommonResult<Boolean> add(@Validated
                                     @RequestBody RecordAddParam param) {
        log.info("添加支出/收入记录： {}", param);
        return CommonResult.success(recordService.add(param));
    }

    @GetMapping("removeById")
    public CommonResult<Boolean> removeById(@Validated
                                            @NotNull(message = "请选择记录")
                                            @RequestParam Long id) {
        log.info("删除支出/收入记录： {}", id);
        return CommonResult.success(recordService.removeById(id));
    }

    @PostMapping("page")
    public CommonResult<RecordPageResult<RecordResult>> page(@Validated
                                                             @RequestBody RecordPageParam param) {
        log.info("分页查询支出/收入记录");
        return CommonResult.success(recordService.page(param));
    }
}
