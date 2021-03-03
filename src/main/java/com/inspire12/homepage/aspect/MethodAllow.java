package com.inspire12.homepage.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MethodAllow {
    enum UserRole {
        USER, GUEST, SHARE, STAFF
    }

    UserRole allow() default UserRole.USER;
}
