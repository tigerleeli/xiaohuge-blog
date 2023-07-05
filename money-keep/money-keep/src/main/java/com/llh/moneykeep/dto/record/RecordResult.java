package com.llh.moneykeep.dto.record;

import com.llh.moneykeep.entity.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecordResult extends Record {
    private String categoryName;

    private String accountName;
}
