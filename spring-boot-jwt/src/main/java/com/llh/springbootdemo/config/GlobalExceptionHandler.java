package com.llh.springbootdemo.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author llh
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult<String> exceptionHandler(Exception e) {
        return CommonResult.error(e.getMessage());
    }
}
