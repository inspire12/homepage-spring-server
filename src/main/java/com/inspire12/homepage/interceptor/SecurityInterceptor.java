package com.inspire12.homepage.interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {

    String env;

    public SecurityInterceptor(String env) {
        this.env = env;
    }

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
//
//        if (this.env.equals("local")){
//            return true;
//        }
//        MethodAllow methodAllow = ((HandlerMethod) handler).getMethodAnnotation(MethodAllow.class);
//
//        if (methodAllow == null || methodAllow.allow().equals(MethodAllow.UserRole.USER)) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication.getPrincipal().equals("anonymousUser")){
//                throw new AccessDeniedException("가입한 후 사용해주세요");
//            }
//            return true;
//        } else if (methodAllow.allow().equals(MethodAllow.UserRole.GUEST)) {
//            return true;
//        }
//        throw new Exception();
        return true;
    }

}
