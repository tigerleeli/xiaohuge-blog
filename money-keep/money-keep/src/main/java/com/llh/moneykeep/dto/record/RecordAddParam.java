package com.llh.moneykeep.dto.record;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RecordAddParam {
    @NotNull(message = "请选择分类")
    private Long categoryId;

    @NotNull(message = "请选择账户")
    private Long accountId;

    @NotNull(message = "请输入金额")
    @DecimalMin(value = "0", message = "金额不能小于0")
    private BigDecimal amount;

    /**
     * 类型，1：支出，2：收入
     */
    @NotNull(message = "请选择类型")
    @Range(min = 1, max = 2, message = "类型选择错误")
    private Integer type;

    private String remark;
}
