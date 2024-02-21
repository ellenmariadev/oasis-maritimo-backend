package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.ErrorResponse;
import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalUpdateDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpecieService specieService;

    public List<AnimalResponseDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(AnimalResponseDTO::new).toList();
    }

    public AnimalResponseDTO createAnimal(AnimalRequestDTO animalRequestDTO) {
        UUID speciesId = animalRequestDTO.speciesId();
        if (speciesId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Species id must not be null");
        }
        Specie species = specieService.getSpecieById(speciesId);
        if (species == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specie with id " + speciesId + " not found");
        }
        Animal animal = new Animal(animalRequestDTO.name(),
                species,
                animalRequestDTO.age(),
                animalRequestDTO.weight(),
                animalRequestDTO.length(),
                animalRequestDTO.habitat(),
                animalRequestDTO.arrivalDate(),
                animalRequestDTO.diet());
        animal = animalRepository.save(animal);
        return new AnimalResponseDTO(animal);
    }

    public Animal findAnimalById(UUID id) {
        return animalRepository.findAnimalById(id);
    }

    public AnimalResponseDTO updateAnimal(UUID id, AnimalUpdateDTO animalUpdateDTO) {
        Animal animal = animalRepository.findAnimalById(id);
        if (animal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal with id " + id + " not found");
        }
        try {
            animalUpdateDTO.name().ifPresent(animal::setName);
            animalUpdateDTO.age().ifPresent(animal::setAge);

            if (animalUpdateDTO.species().isPresent()) {
                String stringId = animalUpdateDTO.species().get();
                UUID speciesId = UUID.fromString(stringId);
                Specie species = specieService.getSpecieById(speciesId);
                if (species == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specie with id " + speciesId + " not found");
                }
                animal.setSpecies(species);
            }

            animal = animalRepository.save(animal);
            return new AnimalResponseDTO(animal);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating animal", e);
        }
    }
    public ResponseEntity<?> deleteAnimal(UUID id) {
        Animal animal = animalRepository.findAnimalById(id);
        if (animal == null) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Animal with id " + id + " not found", "");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        try {
            animalRepository.delete(animal);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error deleting animal", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}