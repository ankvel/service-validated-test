package com.akestrel.edu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * @author Aleksey Kovalenko
 */
public class ValidateAspect {


    private Validator validator;

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(JoinPoint jp) throws NoSuchMethodException {

        Set<ConstraintViolation<?>> violations = new HashSet<ConstraintViolation<?>>();

        Method interfaceMethod = ((MethodSignature)jp.getSignature()).getMethod();
        Method implementationMethod = jp.getTarget().getClass().getMethod(interfaceMethod.getName(), interfaceMethod.getParameterTypes());

        Annotation[][] annotationParameters = implementationMethod.getParameterAnnotations();

        for (int i = 0; i < annotationParameters.length; i++) {
            Annotation[] annotations = annotationParameters[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Validated.class)) {
                    Validated valid = (Validated)annotation;
                    
                    Object arg = jp.getArgs()[i];
                    violations.addAll(validator.validate(arg, valid.value()));
                }
            }
        }
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

    }
}