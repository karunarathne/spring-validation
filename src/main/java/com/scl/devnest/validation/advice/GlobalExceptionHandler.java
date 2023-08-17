package com.scl.devnest.validation.advice;

import com.scl.devnest.validation.dto.APIError;
import com.scl.devnest.validation.dto.FieldError;
import com.scl.devnest.validation.dto.GlobalError;
import com.scl.devnest.validation.exception.DtoNotFoundException;
import com.scl.devnest.validation.exception.DuplicateEmailException;
import com.scl.devnest.validation.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(DtoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDtoNotFound(DtoNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmail(DuplicateEmailException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleValidationFailure(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors().stream().map(err -> {
            FieldError error = new FieldError();
            error.setFieldName(err.getField());
            error.setRejectedValue(err.getRejectedValue().toString());
            error.setMessage(err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());

        List<GlobalError> globalErrors = ex.getBindingResult().getGlobalErrors().stream().map(err -> {
            GlobalError error = new GlobalError();
            error.setMessage(err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());

        APIError error = new APIError();
        error.setGlobalErrors(globalErrors);
        error.setFieldErrors(fieldErrors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(error);
    }
}
