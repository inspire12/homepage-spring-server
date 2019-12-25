package com.inspire12.homepage.interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (modelAndView != null && authentication != null && authentication.isAuthenticated()) {
            String user = (String) authentication.getPrincipal();
            modelAndView.addObject("user", user);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserLevel userLevel = ((HandlerMethod) handler).getMethodAnnotation(UserLevel.class);
        if (userLevel == null || userLevel.equals("null")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.getPrincipal().equals("anonymousUser")){
                throw new Exception();
            }
            return true;
        } else if (userLevel.allow().equals(UserLevel.UserRole.USER)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.getPrincipal().equals("anonymousUser")){
                throw new Exception();
            }
            return true;
        } else if (userLevel.allow().equals(UserLevel.UserRole.GUEST)) {
            return true;
        }
        throw new Exception();
    }

}
