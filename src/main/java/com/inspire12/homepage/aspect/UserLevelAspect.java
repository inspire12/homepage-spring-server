package com.inspire12.homepage.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class UserLevelAspect {

    @Around("@annotation(com.inspire12.homepage.aspect.UserLevel)")
    public Object allow(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        UserLevel userLevel = method.getAnnotation(UserLevel.class);
        Object test1 = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs(); //
//        AppUser user = (AppUser) session.getAttribute("user");
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object test3 = joinPoint.getSignature();
        String a = joinPoint.getKind();
        Object b = joinPoint.getThis();
        Object result = joinPoint.proceed();

        return result;
    }
}
