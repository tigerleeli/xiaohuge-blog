package com.llh.moneykeep.entity;

import com.llh.moneykeep.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 分类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7416379478061737338L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 类型，1：支出，2：收入
     */
    private Integer type;

}
