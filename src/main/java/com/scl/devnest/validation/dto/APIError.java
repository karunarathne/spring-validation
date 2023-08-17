package com.scl.devnest.validation.dto;

import lombok.Data;

import java.util.List;

@Data
public class APIError {

    private List<FieldError> fieldErrors;

    private List<GlobalError> globalErrors;
}
