package com.example.oasismaritimo.exceptions;

import com.example.oasismaritimo.exceptions.core.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ApplicationException {
    public InvalidRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
