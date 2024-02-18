package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<AnimalResponseDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(AnimalResponseDTO::new).toList();
    }

    public AnimalResponseDTO createAnimal(AnimalRequestDTO animalRequestDTO) {
        Animal animal = new Animal(animalRequestDTO);
        animal = animalRepository.save(animal);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO getAnimal(String name) {
        Animal animal = animalRepository.findByName(name);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO updateAnimal(String name, AnimalRequestDTO animalRequestDTO) {
        Animal animal = animalRepository.findByName(name);
        animal.setName(animalRequestDTO.name());
        animal.setSpecies(animalRequestDTO.species());
        animal.setAge(animalRequestDTO.age());
        animal = animalRepository.save(animal);
        return new AnimalResponseDTO(animal);
    }

    public void deleteAnimal(String name) {
        Animal animal = animalRepository.findByName(name);
        animalRepository.delete(animal);
    }
}