package com.llh.moneykeep.dto.record;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.llh.moneykeep.common.BasePageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecordPageResult<T> extends BasePageResult<T> {
    private BigDecimal totalAmount;

    public <E> RecordPageResult(IPage<E> page, List<T> recordList, BigDecimal totalAmount) {
        super(page, recordList);
        this.totalAmount = totalAmount;
    }
}
