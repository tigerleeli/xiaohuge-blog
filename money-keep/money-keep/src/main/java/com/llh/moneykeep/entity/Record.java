package com.llh.moneykeep.entity;

import com.llh.moneykeep.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 记录 支出和收入
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Record extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5814551885045587391L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 类型，1：支出，2：收入
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;
}
