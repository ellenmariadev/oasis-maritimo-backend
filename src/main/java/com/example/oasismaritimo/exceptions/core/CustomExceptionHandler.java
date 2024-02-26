package com.example.oasismaritimo.exceptions.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {
        ErrorResponse errorDetails = new ErrorResponse();
        if (exception instanceof ApplicationException appException) {
            errorDetails.setStatus(appException.getStatus());
            errorDetails.setStatusCode(appException.getStatus().value());
        } else {
            errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            errorDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        errorDetails.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }
}