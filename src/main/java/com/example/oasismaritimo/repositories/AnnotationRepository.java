package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnnotationRepository extends JpaRepository<Annotation, UUID> {
}
