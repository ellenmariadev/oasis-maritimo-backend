package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.model.Animal;

import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<AnimalResponseDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(AnimalResponseDTO::new).toList();
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal findAnimalById(UUID id) {
        Animal animal = animalRepository.findAnimalById(id);
        if (animal == null) {
            throw new NotFoundException("Animal");
        }
        return animal;
    }


    public void deleteAnimal(UUID id) {
        Optional.ofNullable(findAnimalById(id)).orElseThrow(() -> new NotFoundException("Animal"));
        animalRepository.deleteById(id);
    }
}