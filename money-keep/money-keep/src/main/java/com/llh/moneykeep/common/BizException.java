package com.llh.moneykeep.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 123606337409762019L;

    /**
     * 错误码
     */
    private Integer code;

    public BizException(String msg) {
        super(msg);
        this.code = 10000;
    }
}
