package com.llh.moneykeep.entity;

import com.llh.moneykeep.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -313393763609378436L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
