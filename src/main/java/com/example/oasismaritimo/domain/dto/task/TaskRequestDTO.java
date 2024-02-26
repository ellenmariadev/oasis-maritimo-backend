package com.example.oasismaritimo.domain.dto.task;

import com.example.oasismaritimo.domain.enums.StatusTask;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record TaskRequestDTO(
    @NotBlank
    String title,

    String description,

    StatusTask status,

    UUID authorId,

    UUID animalId

){}
