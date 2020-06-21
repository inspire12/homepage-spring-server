package com.inspire12.homepage;

import com.inspire12.homepage.exception.ErrorCode;
import com.inspire12.homepage.exception.ErrorMessage;
import com.inspire12.homepage.exception.NotAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice("com.inspire12.homepage.controller.template.*")
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleNull(Model model, Exception e) {
        System.err.println(e.getClass());
        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("code", 500);
        model.addAttribute("msg", "에러가 발생했니다.");
        model.addAttribute("timestamp", LocalDateTime.now());
        return "auth/error";
    }

    @ExceptionHandler(NotAuthException.class)
    public String handleNotFound(Model model, Exception e) {
        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("code", 401);
        model.addAttribute("msg", "에러가 발생했습니다.");
        model.addAttribute("timestamp", LocalDateTime.now());
        return "auth/error";
    }

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected String handleMethodArgumentNotValidException(Model model, MethodArgumentNotValidException e) {
        logger.error("handleMethodArgumentNotValidException", e);
        final ErrorMessage response = ErrorMessage.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        model.addAttribute("response", new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
        return "auth/error";
    }


    /**
     * @ModelAttribute 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected String handleBindException(Model model, BindException e) {
        logger.error("handleBindException", e);
        final ErrorMessage response = ErrorMessage.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        model.addAttribute("response", new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
        return "auth/error";
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected String handleMethodArgumentTypeMismatchException(Model model, MethodArgumentTypeMismatchException e) {
        logger.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorMessage response = ErrorMessage.of(ErrorCode.INVALID_INPUT_VALUE);
        model.addAttribute("response", new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
        return "auth/error";
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected String handleHttpRequestMethodNotSupportedException(Model model, HttpRequestMethodNotSupportedException e) {
        logger.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorMessage response = ErrorMessage.of(ErrorCode.METHOD_NOT_ALLOWED);

        model.addAttribute("response", new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED));
        return "auth/error";
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected String handleAccessDeniedException(Model model, AccessDeniedException e) {
        logger.error("handleAccessDeniedException", e);
        final ErrorMessage response = ErrorMessage.of(ErrorCode.HANDLE_ACCESS_DENIED);
        model.addAttribute("response", new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE));
        return "auth/error";
    }




}
