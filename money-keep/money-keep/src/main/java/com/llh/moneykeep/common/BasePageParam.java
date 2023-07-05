package com.llh.moneykeep.common;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 分页参数基类
 */
@Data
public class BasePageParam {
    /**
     * 当前页码
     */
    @NotNull(message = "页面不能为空")
    private Long pageNum;

    /**
     * 每页条数
     */
    @Max(value = 2000, message = "每页数量不能超过两千")
    @NotNull(message = "页数不能为空")
    private Long pageSize;
}
