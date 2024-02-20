package com.example.oasismaritimo.domain.dto.task;

import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Task;
import com.example.oasismaritimo.domain.model.User;

import java.util.Date;
import java.util.UUID;

public record TaskResponseDTO(
        UUID id,
        String title,

        String description,

        String status,

        User author,

        Animal animal,

        Date createdAt
) {
   public TaskResponseDTO(Task task) {
       this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAuthor(),
                task.getAnimal(),
                task.getCreatedAt()
       );
   }
}
