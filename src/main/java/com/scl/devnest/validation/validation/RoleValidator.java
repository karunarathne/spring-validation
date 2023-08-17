package com.scl.devnest.validation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class RoleValidator implements ConstraintValidator<ValidRoleType, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roleTypes = List.of("ADMIN", "USER");
        return roleTypes.contains(role);
    }
}
