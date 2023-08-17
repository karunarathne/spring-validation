package com.scl.devnest.validation.dto;

import lombok.Data;

@Data
public class FieldError {
    private String fieldName;
    private String rejectedValue;
    private String message;
}
