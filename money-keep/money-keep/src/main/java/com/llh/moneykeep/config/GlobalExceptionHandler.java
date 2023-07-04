package com.llh.moneykeep.config;

import com.llh.moneykeep.common.BizException;
import com.llh.moneykeep.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * 异常拦截
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 参数验证失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        if (errors.isEmpty()) {
            return CommonResult.error(e.getMessage());
        }
        return CommonResult.error(errors.get(0).getDefaultMessage());
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(BizException.class)
    public CommonResult<Object> bizException(BizException e) {
        return CommonResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<Object> exception(Exception e) {
        e.printStackTrace();
        return CommonResult.error(e.getMessage());
    }
}
