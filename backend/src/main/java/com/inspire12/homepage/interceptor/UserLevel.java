package com.inspire12.homepage.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UserLevel {
    public enum UserRole {
        USER, GUEST, SHARE
    }

    UserRole allow() default UserRole.USER;
}
