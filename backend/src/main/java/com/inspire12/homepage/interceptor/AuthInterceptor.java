package com.inspire12.homepage.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
//        LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
//
//        if(loginVO == null || !loginVO.isValidUser()){
//            response.setContentType("text/html; charset=UTF-8");
//
//            PrintWriter out = response.getWriter();
//
//            out.println("<script>alert('세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.'); window.location.href='/login';</script>");
//            out.flush();
//
//            return false;
//        }

        return true;
    }
}
