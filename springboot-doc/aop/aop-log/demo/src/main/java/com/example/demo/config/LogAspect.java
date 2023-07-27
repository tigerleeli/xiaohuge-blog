package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
public class LogAspect {

    // 定义需要被拦截的切点
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void pointcut() {
    }

    // 在方法执行时进行通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            System.out.println("日志名称：" + sysLog.name());
        }
        System.out.println("开始时间：" + LocalDateTime.now());
        System.out.println("执行方法 " + joinPoint.getSignature().getName() + " 前置环绕通知");
        Object o = joinPoint.proceed();
        System.out.println("结束时间：" + LocalDateTime.now());
        System.out.println("执行方法 " + joinPoint.getSignature().getName() + " 后置环绕通知");
        return o;
    }

    // 在方法执行之前进行通知
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("执行方法 " + joinPoint.getSignature().getName() + " 前置通知");
    }

    // 在方法执行之后进行通知
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("执行方法 " + joinPoint.getSignature().getName() + " 后置通知");
    }

    // 在方法执行之后返回结果时进行通知
    @AfterReturning(returning = "result", pointcut = "pointcut()")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("执行方法 " + joinPoint.getSignature().getName() + " 返回通知，返回值：" + result);
    }

    // 在方法抛出异常时进行通知
    @AfterThrowing(throwing = "ex", pointcut = "pointcut()")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.err.println("执行方法 " + joinPoint.getSignature().getName() + " 异常通知，异常：" + ex.getMessage());
    }
}
