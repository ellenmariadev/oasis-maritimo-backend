package com.example.oasismaritimo.domain.dto.task;

import com.example.oasismaritimo.domain.enums.StatusTask;

import java.util.Optional;
import java.util.UUID;

public record TaskUpdateDTO(
        Optional<String> title,
        Optional<String> description,
        Optional<StatusTask> status,
        Optional<UUID> authorId,
        Optional<UUID> animalId
) {}