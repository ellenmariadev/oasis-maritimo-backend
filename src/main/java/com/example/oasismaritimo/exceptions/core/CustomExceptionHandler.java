package com.example.oasismaritimo.exceptions.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    private ResponseEntity<ErrorResponse> applicationExceptionHandler(ApplicationException exception) {
        ErrorResponse response = new ErrorResponse(exception.getStatus(), exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(response);
    }
}