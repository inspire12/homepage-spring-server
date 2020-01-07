package com.inspire12.homepage;

import com.inspire12.homepage.exception.NotAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleNull(Model model, Exception e) {
        System.err.println(e.getClass());
        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("code", 500);
        model.addAttribute("msg", "에러가 발생했씁니다.");
        model.addAttribute("timestamp", LocalDateTime.now());
        return "auth/error";
    }

    @ExceptionHandler(NotAuthException.class)
    public String handleNotFound(Model model, Exception e) {
        System.err.println();
        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("code", 401);
        model.addAttribute("msg", "에러가 발생했씁니다.");
        model.addAttribute("timestamp", LocalDateTime.now());
        return "auth/error";
    }
}
