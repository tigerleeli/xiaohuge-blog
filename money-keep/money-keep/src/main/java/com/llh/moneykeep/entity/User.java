package com.llh.moneykeep.entity;

import com.llh.moneykeep.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -313393763609378436L;

    private String username;

    private String password;
}
