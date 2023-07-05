package com.llh.moneykeep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llh.moneykeep.common.BasePageParam;
import com.llh.moneykeep.common.BasePageResult;
import com.llh.moneykeep.dto.account.AccountAddParam;
import com.llh.moneykeep.dto.category.CategoryAddParam;
import com.llh.moneykeep.dto.record.*;
import com.llh.moneykeep.entity.Account;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.entity.Record;

import java.math.BigDecimal;
import java.util.List;

public interface RecordService extends IService<Record> {
    Boolean add(RecordAddParam param);

    Boolean removeById(Long id);

    RecordPageResult<RecordResult> page(RecordPageParam param);

    BigDecimal statsTotalAmount(AmountStatsParam param);
}
