package com.example.oasismaritimo.domain.dto.annotation;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

public record AnnotationRequestDTO(
        @NotBlank
        String title,

        String description,

        UUID authorId,

        UUID animalId,

        Set<UUID> tags
) { }
