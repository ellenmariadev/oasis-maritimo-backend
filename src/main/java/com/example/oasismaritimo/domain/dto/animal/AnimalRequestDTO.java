package com.example.oasismaritimo.domain.dto.animal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public record AnimalRequestDTO (
        @NotBlank
        String name,
        @NotNull
        UUID speciesId,
        @NotNull
        Integer age,
        Date arrivalDate,
        String diet,
        @NotNull
        Double weight,
        @NotNull
        Double length,
        String habitat,
        String imageUrl
) {}
