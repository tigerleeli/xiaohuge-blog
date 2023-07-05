package com.llh.moneykeep.dto.record;


public enum RecordType {
    // 支出
    COST(1),

    // 收入
    INCOME(2);

    private Integer value;

    RecordType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
