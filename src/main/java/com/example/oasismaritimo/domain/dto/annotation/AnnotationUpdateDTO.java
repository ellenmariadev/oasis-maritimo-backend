package com.example.oasismaritimo.domain.dto.annotation;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public record AnnotationUpdateDTO(
        Optional<String> title,
        Optional<String> description,
        Optional<UUID> animalId,
        Optional<Set<UUID>> tags
   ) { }
