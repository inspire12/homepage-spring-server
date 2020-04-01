package com.inspire12.homepage;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.err.println(e.getClass());

        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNull(Exception e) {
        System.err.println(e.getClass());
        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(Exception e) {
        System.err.println();

        logger.error("["+e.getClass() + "] " + e.getMessage() + " from user:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentException(MethodArgumentNotValidException e) {
        return e.getMessage();
    }
}
