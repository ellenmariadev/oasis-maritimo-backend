package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalFacade {
    @Autowired
    private AnimalService animalService;

    public List<AnimalResponseDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }
    public AnimalResponseDTO createAnimal(AnimalRequestDTO animalRequestDTO) {
        return animalService.createAnimal(animalRequestDTO);
    }

    public AnimalResponseDTO getAnimal(String name) {
        return animalService.getAnimal(name);
    }

    public AnimalResponseDTO updateAnimal(String name, AnimalRequestDTO animalRequestDTO) {
        return animalService.updateAnimal(name, animalRequestDTO);
    }

    public void deleteAnimal(String name) {
        animalService.deleteAnimal(name);
    }
}