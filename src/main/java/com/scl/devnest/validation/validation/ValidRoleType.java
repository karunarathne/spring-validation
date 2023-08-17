package com.scl.devnest.validation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented@Constraint(validatedBy = RoleValidator.class)
public @interface ValidRoleType {
    public String message() default "Invalid user role. Only ADMIN or USER can be the role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
