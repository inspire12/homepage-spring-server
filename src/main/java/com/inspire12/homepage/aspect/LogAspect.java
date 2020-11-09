package com.inspire12.homepage.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {

    @Around("execution(* com.inspire12.homepage.controller())")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        try{

        }catch(Exception e) {

        }
        return joinPoint.proceed();
    }
}
