package com.inspire12.homepage.aspect;

import com.inspire12.homepage.dto.user.AppUserInfo;
import com.inspire12.homepage.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Slf4j
public class MethodAllowAspect {
    @Around("@annotation(com.inspire12.homepage.aspect.MethodAllow)")
    public Object allow(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            MethodAllow methodAllow = method.getAnnotation(MethodAllow.class);
            if (methodAllow.allow() != MethodAllow.UserRole.GUEST) {
                HttpSession session = (HttpSession) joinPoint.getArgs()[0];
                AppUserInfo userInfo = (AppUserInfo) session.getAttribute("user");

                if (Objects.isNull(userInfo.getUserId()) || (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())) {
                    log.warn("authentication error");
                    throw new CommonException();
                }
            }
        } catch (Exception e) {
            log.warn("user_level_aspect exception : {}", e.toString());
        }
        return joinPoint.proceed();
    }
}