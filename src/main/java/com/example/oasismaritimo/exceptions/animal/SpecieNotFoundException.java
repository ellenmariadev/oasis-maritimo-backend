package com.example.oasismaritimo.exceptions.animal;

import com.example.oasismaritimo.exceptions.core.ApplicationException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class SpecieNotFoundException extends ApplicationException {
    public SpecieNotFoundException(UUID id) {
        super("Specie with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
}
