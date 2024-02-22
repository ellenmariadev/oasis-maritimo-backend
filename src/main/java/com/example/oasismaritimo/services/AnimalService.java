package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.ErrorResponse;
import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalUpdateDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.infra.aws.ImageService;
import com.example.oasismaritimo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SpecieService specieService;
    @Autowired
    private ImageService imageService;
    @Value("${s3.bucket.name}")
    private String bucketName;

    public List<AnimalResponseDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(AnimalResponseDTO::new).toList();
    }

    public AnimalResponseDTO createAnimal(MultipartFile image, AnimalRequestDTO animalRequestDTO) throws Exception {
        // Extrai a extensão do arquivo original
        String originalFileName = image.getOriginalFilename();
        String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // Cria um arquivo temporário com a extensão do arquivo original
        File tempFile = File.createTempFile("image", originalFileExtension);
        // Transfere o MultipartFile para o arquivo temporário
        image.transferTo(tempFile);

        String imageUrl = imageService.uploadImage(tempFile, bucketName);
        AnimalRequestDTO animalRequest = new AnimalRequestDTO(
                animalRequestDTO.name(),
                animalRequestDTO.speciesId(),
                animalRequestDTO.age(),
                animalRequestDTO.arrivalDate(),
                animalRequestDTO.diet(),
                animalRequestDTO.weight(),
                animalRequestDTO.length(),
                animalRequestDTO.habitat(),
                imageUrl
        );
        
        UUID speciesId = animalRequest.speciesId();
        if (speciesId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Species id must not be null");
        }
        Specie species = specieService.getSpecieById(speciesId);
        if (species == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specie with id " + speciesId + " not found");
        }
        Animal animal = new Animal(animalRequestDTO, species, imageUrl);
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