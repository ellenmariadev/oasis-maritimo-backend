package com.example.oasismaritimo.exceptions;

import com.example.oasismaritimo.exceptions.core.ApplicationException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String resourceName) {
        super(resourceName + " não encontrado(a).",  HttpStatus.NOT_FOUND);
    }
}