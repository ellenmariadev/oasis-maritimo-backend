package com.example.oasismaritimo.domain.dto.animal;

import java.util.Date;
import java.util.Optional;

public record AnimalUpdateDTO(
        Optional<String> name,
        Optional<String> species,
        Optional<Integer> age,
        Optional<Date> arrivalDate,
        Optional<String> diet,
        Optional<Double> weight,
        Optional<Double> length,
        Optional<String> habitat
) {}