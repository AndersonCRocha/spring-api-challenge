package com.recruitment.challenge.utilities.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE, METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValuesMatchValidator.class)
@Documented
public @interface ValuesMatch {
 
    String message() default "Values doesn't match!";
 
    String field();
 
    String fieldMatch();
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
 
}
