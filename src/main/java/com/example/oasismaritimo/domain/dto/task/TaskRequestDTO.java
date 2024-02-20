package com.example.oasismaritimo.domain.dto.task;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record TaskRequestDTO(
    @NotBlank
    String title,

    String description,

    String status,

    UUID authorId,

    UUID animalId

){}
