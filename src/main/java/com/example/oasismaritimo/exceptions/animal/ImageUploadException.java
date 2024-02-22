package com.example.oasismaritimo.exceptions.animal;

import com.example.oasismaritimo.exceptions.core.ApplicationException;
import org.springframework.http.HttpStatus;

public class ImageUploadException extends ApplicationException {
    public ImageUploadException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}