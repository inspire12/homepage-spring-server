package com.inspire12.homepage;

import com.inspire12.homepage.exception.ErrorCode;
import com.inspire12.homepage.exception.ErrorMessage;
import com.inspire12.homepage.exception.NotAuthorizeException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        log.error("exception from user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NotAuthorizeException.class)
    public ResponseEntity handleNotAuthorize(NotAuthorizeException e) {
        log.error("unauthorize from user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNull(NullPointerException e) {
        log.error("exception from user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(NotFoundException e) {
        log.error("exception from user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElement(NoSuchElementException e) {
        log.error("exception from user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(ErrorMessage.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = ConstraintViolationException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    protected ResponseEntity handleException(ConstraintViolationException e) {
//        exception.getConstraintViolations().iterator();
        log.error("exception from user {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e);
        return ResponseEntity.status(400).body(ErrorMessage.of(ErrorCode.INVALID_INPUT_VALUE));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
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
