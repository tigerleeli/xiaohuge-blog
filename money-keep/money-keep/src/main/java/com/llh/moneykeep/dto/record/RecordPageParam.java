package com.llh.moneykeep.dto.record;

import com.llh.moneykeep.common.BasePageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecordPageParam extends BasePageParam {
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
