package com.example.oasismaritimo.domain.dto.annotation;

import com.example.oasismaritimo.domain.dto.task.TaskResponseDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Annotation;
import com.example.oasismaritimo.domain.model.Tag;
import com.example.oasismaritimo.domain.model.User;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record AnnotationResponseDTO(
        UUID id,

        String title,

        String description,

        Set<Tag> tags,

        User author,

        Animal animal,

        Date createdAt
) {
        public AnnotationResponseDTO(Annotation annotation) {
                this(
                        annotation.getId(),
                        annotation.getTitle(),
                        annotation.getDescription(),
                        annotation.getTags(),
                        annotation.getAuthor(),
                        annotation.getAnimal(),
                        annotation.getCreatedAt()
                );
        }
}
