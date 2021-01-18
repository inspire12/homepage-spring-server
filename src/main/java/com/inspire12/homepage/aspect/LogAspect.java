package com.inspire12.homepage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Around("execution(* com.inspire12.homepage.service.user.UserService.*(..))")
//    @Around("execution(* com.inspire12.homepage.controller())")
//    @Around("execution(* *(.., @LogAdvice (*), ..))") // 파라미터 어노테이션 적용 1번째 *은 리턴타입을 나타내며 두 번째부터 시작하는 *(.., @User (*), ..)은 @User 애노테이션이 선언된 부분의 양옆의 다른 파라미터 0개 이상을 허용하겠다는 패턴
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
//        String methodName = joinPoint.getSignature().toShortString();
//        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
//        log.info("log method: {}", methodName);

        log.warn("start - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.warn("finished - {} / {}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        return result;
    }
}
