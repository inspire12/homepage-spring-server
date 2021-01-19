package com.inspire12.homepage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class UserLevelAspect {

    @Around("@annotation(com.inspire12.homepage.aspect.UserLevel)")
    public Object allow(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            UserLevel userLevel = method.getAnnotation(UserLevel.class);
        } catch (Exception e) {
            log.warn("user_level_aspect exception : {}", e.toString());
        }
//        Object test1 = joinPoint.getTarget();
//        Object[] args = joinPoint.getArgs(); //
////        AppUser user = (AppUser) session.getAttribute("user");
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Object test3 = joinPoint.getSignature();
//        String a = joinPoint.getKind();
//        Object b = joinPoint.getThis();
        Object result = joinPoint.proceed();

        return result;
    }
}
