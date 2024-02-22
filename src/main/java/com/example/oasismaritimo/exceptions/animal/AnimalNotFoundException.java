package com.example.oasismaritimo.exceptions.animal;

import java.util.UUID;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(UUID id) {
        super("Animal with id " + id + " not found");
    }
}