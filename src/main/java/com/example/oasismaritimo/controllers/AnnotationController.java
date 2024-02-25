package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.annotation.AnnotationRequestDTO;
import com.example.oasismaritimo.domain.dto.annotation.AnnotationUpdateDTO;
import com.example.oasismaritimo.domain.model.Annotation;
import com.example.oasismaritimo.facade.AnnotationFacade;
import com.example.oasismaritimo.services.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/annotations")
public class AnnotationController {
    private final AnnotationFacade annotationFacade;

    @Autowired
    public AnnotationController(AnnotationFacade annotationFacade) {
        this.annotationFacade = annotationFacade;
    }

    @GetMapping
    public List<Annotation> getAllAnnotations() {
        return annotationFacade.getAllAnnotations();
    }

    @GetMapping("/{id}")
    public Annotation getAnnotationById(@PathVariable("id") UUID id) {
        return annotationFacade.getAnnotationById(id);
    }

    @PostMapping
    public Annotation createAnnotation(@RequestBody AnnotationRequestDTO annotation) {
        return annotationFacade.createAnnotation(annotation);
    }

    @PutMapping("/{id}")
    public Annotation updateAnnotation(@PathVariable("id") UUID id, @RequestBody AnnotationUpdateDTO annotationUpdateDTO) {
        return annotationFacade.updateAnnotation(id, annotationUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnotation(@PathVariable("id") UUID id) {
        annotationFacade.deleteAnnotation(id);
    }
}
