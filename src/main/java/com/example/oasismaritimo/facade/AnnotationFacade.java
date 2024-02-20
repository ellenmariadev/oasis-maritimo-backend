package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.annotation.AnnotationRequestDTO;
import com.example.oasismaritimo.domain.dto.annotation.AnnotationUpdateDTO;
import com.example.oasismaritimo.domain.model.Annotation;
import com.example.oasismaritimo.services.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AnnotationFacade {
    private final AnnotationService annotationService;

    @Autowired
    public AnnotationFacade(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    public List<Annotation> getAllAnnotations() {
        return annotationService.getAllAnnotations();
    }

    public Annotation getAnnotationById(UUID id) {
        return annotationService.getAnnotationById(id);
    }

    public Annotation createAnnotation(AnnotationRequestDTO annotation) {
        return annotationService.createAnnotation(annotation);
    }

    public Annotation updateAnnotation(UUID id, AnnotationUpdateDTO annotationUpdateDTO) {
        return annotationService.updateAnnotation(id, annotationUpdateDTO);
    }
    public void deleteAnnotation(UUID id) {
        annotationService.deleteAnnotation(id);
    }
}
