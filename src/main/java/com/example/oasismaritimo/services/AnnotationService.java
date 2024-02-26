package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.annotation.AnnotationRequestDTO;
import com.example.oasismaritimo.domain.dto.annotation.AnnotationUpdateDTO;
import com.example.oasismaritimo.domain.model.*;
import com.example.oasismaritimo.exceptions.InvalidRequestException;
import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.repositories.AnimalRepository;
import com.example.oasismaritimo.repositories.AnnotationRepository;
import com.example.oasismaritimo.repositories.TagRepository;
import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return annotationRepository.findById(id).orElseThrow(() -> new NotFoundException("Anotação"));
    }

    public Annotation createAnnotation(AnnotationRequestDTO annotationRequestDTO) {
        User author = null;
        Animal animal = null;
        Set<Tag> tags = new HashSet<>();
        if (annotationRequestDTO.authorId() != null) {
            author = userRepository.findById(annotationRequestDTO.authorId()).orElseThrow(() -> new InvalidRequestException("Autor inválido."));
        }
        if (annotationRequestDTO.animalId() != null) {
            animal = animalRepository.findById(annotationRequestDTO.animalId()).orElseThrow(() -> new InvalidRequestException("Animal inválido."));
        }
        if (annotationRequestDTO.tags() != null && !annotationRequestDTO.tags().isEmpty()) {
            tags.addAll(tagRepository.findAllById(annotationRequestDTO.tags()));
        }
        Annotation annotation = new Annotation(annotationRequestDTO, author, animal, tags);
        annotation = annotationRepository.save(annotation);
        return annotation;
    }

    public Annotation updateAnnotation(UUID id, AnnotationUpdateDTO annotationUpdateDTO) {
        Annotation annotation = annotationRepository.findById(id).orElseThrow(() -> new NotFoundException("Anotação"));

        annotationUpdateDTO.title().ifPresent(annotation::setTitle);
        annotationUpdateDTO.description().ifPresent(annotation::setDescription);

        annotationUpdateDTO.animalId().ifPresent(animalId -> {
            Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new NotFoundException("Animal"));
            annotation.setAnimal(animal);
        });

        annotationUpdateDTO.tags().ifPresent(tagIds -> {
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(tagIds));
            annotation.setTags(tags);
        });

        return annotationRepository.save(annotation);
    }
    public void deleteAnnotation(UUID id) {
        Optional.ofNullable(getAnnotationById(id)).orElseThrow(() -> new NotFoundException("Anotação"));
        annotationRepository.deleteById(id);
    }
}