package com.inspire12.homepage.aspect;

import lombok.RequiredArgsConstructor;

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
        return value.length() < 3;
    }
}
