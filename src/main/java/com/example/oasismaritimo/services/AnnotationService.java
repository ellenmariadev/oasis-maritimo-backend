package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.annotation.AnnotationRequestDTO;
import com.example.oasismaritimo.domain.dto.annotation.AnnotationUpdateDTO;
import com.example.oasismaritimo.domain.model.*;
import com.example.oasismaritimo.repositories.AnimalRepository;
import com.example.oasismaritimo.repositories.AnnotationRepository;
import com.example.oasismaritimo.repositories.TagRepository;
import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AnnotationService {
    private final AnnotationRepository annotationRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public AnnotationService(AnnotationRepository annotationRepository, TagRepository tagRepository, UserRepository userRepository, AnimalRepository animalRepository) {
        this.annotationRepository = annotationRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    public List<Annotation> getAllAnnotations() {
        return annotationRepository.findAll();
    }

    public Annotation getAnnotationById(UUID id) {
        return annotationRepository.findById(id).orElse(null);
    }

    public Annotation createAnnotation(AnnotationRequestDTO annotationRequestDTO) {
        User author = null;
        Animal animal = null;
        Set<Tag> tags = new HashSet<>();
        if (annotationRequestDTO.authorId() != null) {
            author = userRepository.findById(annotationRequestDTO.authorId()).orElse(null);
        }
        if (annotationRequestDTO.animalId() != null) {
            animal = animalRepository.findById(annotationRequestDTO.animalId()).orElse(null);
        }
        if (annotationRequestDTO.tags() != null && !annotationRequestDTO.tags().isEmpty()) {
            tags.addAll(tagRepository.findAllById(annotationRequestDTO.tags()));
        }
        Annotation annotation = new Annotation(annotationRequestDTO, author, animal, tags);
        annotation = annotationRepository.save(annotation);
        return annotation;
    }

    public Annotation updateAnnotation(UUID id, AnnotationUpdateDTO annotationUpdateDTO) {
        Annotation annotation = annotationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annotation not found"));

        annotationUpdateDTO.title().ifPresent(annotation::setTitle);
        annotationUpdateDTO.description().ifPresent(annotation::setDescription);

        annotationUpdateDTO.animalId().ifPresent(animalId -> {
            Animal animal = animalRepository.findById(animalId)
                    .orElseThrow(() -> new RuntimeException("Animal not found"));
            annotation.setAnimal(animal);
        });

        annotationUpdateDTO.tags().ifPresent(tagIds -> {
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(tagIds));
            annotation.setTags(tags);
        });

        return annotationRepository.save(annotation);
    }
    public void deleteAnnotation(UUID id) {
        annotationRepository.deleteById(id);
    }
}