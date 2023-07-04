package com.llh.moneykeep.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 上下文对象
 **/
@Data
@AllArgsConstructor
public class ContextObject {
    /**
     * 用户id
     */
    private Long userId;
}
