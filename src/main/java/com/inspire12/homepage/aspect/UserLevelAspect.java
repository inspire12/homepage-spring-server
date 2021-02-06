package com.inspire12.homepage.aspect;

import com.inspire12.homepage.dto.user.AppUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class UserLevelAspect {

    @Around("@annotation(com.inspire12.homepage.aspect.MethodAllow)")
    public Object allow(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            MethodAllow methodAllow = method.getAnnotation(MethodAllow.class);
            if (methodAllow.allow() != MethodAllow.UserRole.GUEST) {
                HttpSession session = (HttpSession) joinPoint.getArgs()[0];
                log.info("session check {}", session);
                AppUserInfo userInfo = (AppUserInfo) session.getAttribute("user");

                if (Objects.isNull(userInfo.getUserId())) {
                    log.warn("authentication error");
                }
                if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){

                }
            }
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
        return joinPoint.proceed();
    }
}
