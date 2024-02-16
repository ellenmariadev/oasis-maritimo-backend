package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.models.Animal;
import com.example.oasismaritimo.repository.AnimalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping()
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{name}")
    public Animal getAnimal(@PathVariable("name") String name) {
        return animalRepository.findByName(name);
    }
}

