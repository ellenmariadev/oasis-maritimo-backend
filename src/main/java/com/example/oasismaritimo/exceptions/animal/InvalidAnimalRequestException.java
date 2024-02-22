package com.example.oasismaritimo.exceptions.animal;

import com.example.oasismaritimo.exceptions.core.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidAnimalRequestException extends ApplicationException {
    public InvalidAnimalRequestException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
