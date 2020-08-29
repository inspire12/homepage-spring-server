package com.inspire12.homepage.aspect;

import com.inspire12.homepage.controller.community.ArticleController;
import com.inspire12.homepage.exception.ErrorMessage;
import com.inspire12.homepage.exception.NotAuthException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@RequiredArgsConstructor
public class TextContentValidator implements ConstraintValidator<TextContentAspect, CharSequence> {

    @Override
    public void initialize(TextContentAspect constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
//        ConstraintValidatorContext context1 = context.buildConstraintViolationWithTemplate( "아니");
//        context.buildConstraintViolationWithTemplate()
        if (value.length() >= 3) {
            return false;
        }
        return true;
    }


}
