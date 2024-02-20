package com.example.oasismaritimo.domain.dto.task;

import java.util.Optional;
import java.util.UUID;

public record TaskUpdateDTO(
        Optional<String> title,
        Optional<String> description,
        Optional<String> status,
        Optional<UUID> authorId,
        Optional<UUID> animalId
) {}