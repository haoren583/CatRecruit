package com.cat.recruit.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author cat
 *
 * 全局日志类
 *
 */
@Slf4j
@Aspect
@Component
public class LoggerGlobal {
    /**
     * 切入点：拦截 com.cat 包及其子包下的所有类的所有方法
     * 你可以根据需要调整，比如只拦截 Service 层或 Controller 层
     */
    @Pointcut("execution(* com.cat.recruit.controller..*.*(..))") // 注意这里是 .. 表示包含子包
    public void applicationPackagePointcut() {
    }

    /**
     * 方法执行前记录日志
     */
    @Before("applicationPackagePointcut()")
    public void  logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.debug("[BEFORE] 调用方法: {}.{}(), 参数: {}", className, methodName, args);
    }

    /**
     * 方法正常返回后记录日志
     */
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        if (log.isDebugEnabled()) {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();

            log.debug("[AFTER_RETURNING] 方法: {}.{}() 执行完成, 返回值: {}", className, methodName, result);
        }
    }

    /**
     * 方法抛出异常后记录日志
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.error("[AFTER_THROWING] 方法: {}.{}() 抛出异常: {}", className, methodName, exception.getMessage(), exception);
    }

    /**
     * 方法执行后（无论是否异常都会执行），记录方法结束，但不区分正常/异常
     */
    @After("applicationPackagePointcut()")
    public void logAfter(JoinPoint joinPoint) {
        if (log.isTraceEnabled()) {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            log.trace("[AFTER] 方法: {}.{}() 执行结束", className, methodName);
        }
    }
}
