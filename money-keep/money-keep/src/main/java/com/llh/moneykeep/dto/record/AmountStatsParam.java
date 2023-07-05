package com.llh.moneykeep.dto.record;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AmountStatsParam {
    /**
     * 开始日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 账户id
     */
    private Long accountId;
}
