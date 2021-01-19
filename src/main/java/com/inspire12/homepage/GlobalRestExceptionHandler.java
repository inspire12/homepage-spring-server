package com.inspire12.homepage;

import com.inspire12.homepage.exception.ErrorCode;
import com.inspire12.homepage.exception.ErrorMessage;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
@ResponseBody
public class GlobalRestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.err.println(e.getClass());

        logger.error("[{}] from user {}", e.getMessage(), SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNull(NullPointerException e) {
        System.err.println(e.getClass());
        logger.error("[{}] from user {}", e.getMessage(), SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(NotFoundException e) {
        System.err.println();

        logger.error("[{}] from user {}", e.getMessage(), SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElement(NoSuchElementException e) {
        System.err.println();

        logger.error("[{}] from user {}", e.getMessage(), SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(ErrorMessage.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = ConstraintViolationException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    protected ResponseEntity handleException(ConstraintViolationException exception) {
        exception.getConstraintViolations().iterator();

        return ResponseEntity.status(400).body(ErrorMessage.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage());
        final BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getRejectedValue());
            System.out.println(error.getField());
            if (error.getRejectedValue().toString().length() >= 3000) {

            }
        }
        return ResponseEntity.status(400).build();
//        return buildFieldErrors(
//                ErrorCode.INPUT_VALUE_INVALID,
//                errors.parallelStream()
//                        .map(error -> ErrorResponse.FieldError.builder()
//                                .reason(error.getDefaultMessage())
//                                .field(error.getField())
//                                .value((String) error.getRejectedValue())
//                                .build())
//                        .collect(Collectors.toList())
//        );
    }

}
