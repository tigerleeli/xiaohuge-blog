package com.llh.moneykeep.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回结果的封装类
 * 10000 成功
 * 20000 失败
 */
@Data
@AllArgsConstructor
public class CommonResult<T> {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回提示信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(10000, "操作成功", data);
    }


    public static <T> CommonResult<T> error(Integer code, String msg) {
        return new CommonResult<T>(code, msg, null);
    }

    public static <T> CommonResult<T> error(String msg) {
        return new CommonResult<T>(20000, msg, null);
    }

}
