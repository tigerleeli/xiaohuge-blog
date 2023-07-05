package com.llh.moneykeep.entity;

import com.llh.moneykeep.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账户
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8247948723383087318L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 账户名称
     */
    private String name;

    /**
     * 账户余额
     */
    private BigDecimal balance;
}
