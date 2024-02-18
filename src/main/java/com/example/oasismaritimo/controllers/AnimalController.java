package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.facade.AnimalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {
    @Autowired
    private AnimalFacade animalFacade;

    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> getAllAnimals() {
        return ResponseEntity.ok(animalFacade.getAllAnimals());
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> createAnimal(@RequestBody AnimalRequestDTO animalRequestDTO) {
        return ResponseEntity.ok(animalFacade.createAnimal(animalRequestDTO));
    }

    @GetMapping("/{name}")
    public ResponseEntity<AnimalResponseDTO> getAnimal(@PathVariable String name) {
        return ResponseEntity.ok(animalFacade.getAnimal(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<AnimalResponseDTO> updateAnimal(@PathVariable String name, @RequestBody AnimalRequestDTO animalRequestDTO) {
        return ResponseEntity.ok(animalFacade.updateAnimal(name, animalRequestDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable String name) {
        animalFacade.deleteAnimal(name);
        return ResponseEntity.noContent().build();
    }
}