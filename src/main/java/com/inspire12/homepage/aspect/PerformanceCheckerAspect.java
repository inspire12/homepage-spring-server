package com.inspire12.homepage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Slf4j
public class PerformanceCheckerAspect {
    @Around("@annotation(com.inspire12.homepage.aspect.PerformanceChecker)")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime startTime = LocalDateTime.now();
        log.info("performance check start {} :{}", joinPoint.getSignature().getName(), startTime);
        Object result = joinPoint.proceed();
        LocalDateTime endTime = LocalDateTime.now();
        log.info("performance check end {} :{}, elapsed: ", joinPoint.getSignature().getName(), Duration.between(startTime, endTime));
        return result;
    }
}
