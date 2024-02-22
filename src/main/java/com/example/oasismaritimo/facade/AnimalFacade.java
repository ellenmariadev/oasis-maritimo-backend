package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalUpdateDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.exceptions.animal.AnimalNotFoundException;
import com.example.oasismaritimo.exceptions.animal.InvalidAnimalRequestException;
import com.example.oasismaritimo.exceptions.animal.SpecieNotFoundException;
import com.example.oasismaritimo.infra.aws.ImageService;
import com.example.oasismaritimo.services.AnimalService;
import com.example.oasismaritimo.services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class AnimalFacade {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private SpecieService specieService;
    @Autowired
    private ImageService imageService;
    @Value("${s3.bucket.name}")
    private String bucketName;

    public List<AnimalResponseDTO> getAllAnimals() {
        return animalService.getAllAnimals();
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

        if (animalRequestDTO.name() == null || animalRequestDTO.speciesId() == null) {
            throw new InvalidAnimalRequestException("Name and speciesId must not be null");
        }
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
            throw new SpecieNotFoundException(speciesId);
        }
        Animal animal = new Animal(animalRequestDTO, species, imageUrl);
        animal = animalService.createAnimal(animal);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO getAnimal(UUID id) {
        Animal animal = animalService.findAnimalById(id);
        return new AnimalResponseDTO(animal);
    }
    public AnimalResponseDTO updateAnimal(UUID id, AnimalUpdateDTO animalUpdateDTO) {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            throw new AnimalNotFoundException(id);
        }
        animalUpdateDTO.name().ifPresent(animal::setName);
        animalUpdateDTO.age().ifPresent(animal::setAge);

        if (animalUpdateDTO.species().isPresent()) {
            String stringId = animalUpdateDTO.species().get();
            UUID speciesId = UUID.fromString(stringId);
            Specie species = specieService.getSpecieById(speciesId);
            if (species == null) {
                throw new SpecieNotFoundException(speciesId);
            }
            animal.setSpecies(species);
        }

        animal = animalService.updateAnimal(animal);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO updateAnimalImage(UUID id, MultipartFile image) throws Exception {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            throw new AnimalNotFoundException(id);
        }

        String originalFileName = image.getOriginalFilename();
        String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        File tempFile = File.createTempFile("image", originalFileExtension);

        image.transferTo(tempFile);

        String imageUrl = imageService.uploadImage(tempFile, bucketName);
        animal.setImageUrl(imageUrl);
        animal = animalService.updateAnimal(animal);
        return new AnimalResponseDTO(animal);
    }

    public void deleteAnimal(UUID id) {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            throw new AnimalNotFoundException(id);
        }
        animalService.deleteAnimal(animal);
    }
}