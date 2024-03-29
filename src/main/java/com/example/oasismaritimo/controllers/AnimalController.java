package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalUpdateDTO;
import com.example.oasismaritimo.facade.AnimalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<AnimalResponseDTO> createAnimal(@RequestParam("image") MultipartFile image, @RequestPart("animal") AnimalRequestDTO animalRequestDTO) throws Exception {
            return ResponseEntity.ok(animalFacade.createAnimal(image, animalRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> getAnimal(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(animalFacade.getAnimal(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> updateAnimal(@PathVariable("id") UUID id, @RequestBody AnimalUpdateDTO animalUpdateDto) {
        return ResponseEntity.ok(animalFacade.updateAnimal(id, animalUpdateDto));
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimalResponseDTO> updateAnimalImage(@PathVariable("id") UUID id,
                                                               @RequestPart("image") MultipartFile image) throws Exception {
        return ResponseEntity.ok(animalFacade.updateAnimalImage(id, image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable("id")  UUID id) {
        animalFacade.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}