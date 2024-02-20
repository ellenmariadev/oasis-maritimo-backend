package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalUpdateDTO;
import com.example.oasismaritimo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public AnimalResponseDTO getAnimal(UUID id) {
        return animalService.getAnimal(id);
    }

    public AnimalResponseDTO updateAnimal(UUID id, AnimalUpdateDTO animalUpdateDTO) {
        return animalService.updateAnimal(id, animalUpdateDTO);
    }

    public void deleteAnimal(UUID id) {
        animalService.deleteAnimal(id);
    }
}