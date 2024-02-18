package com.example.oasismaritimo.domain.dto.animal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AnimalRequestDTO (
 @NotBlank
 String name,
 String species,

 @NotNull
 Integer age
) {}
