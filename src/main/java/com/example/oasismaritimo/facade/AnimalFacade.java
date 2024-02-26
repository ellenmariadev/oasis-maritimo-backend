package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalResponseDTO;
import com.example.oasismaritimo.domain.dto.animal.AnimalUpdateDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.exceptions.InvalidRequestException;
import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.infra.aws.ImageService;
import com.example.oasismaritimo.services.AnimalService;
import com.example.oasismaritimo.services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
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

        // Permitir enviar apenas arquitvos no formato de imagens
        List<String> allowedExtensions = List.of(".png", ".jpg", ".svg", ".webp");
        if (!allowedExtensions.contains(originalFileExtension.toLowerCase())) {
            throw new InvalidRequestException("Tipo de arquivo inválido. Apenas PNG, JPG, SVG, e WEBP são permitidos.");
        }

        // Cria um arquivo temporário com a extensão do arquivo original
        File tempFile = File.createTempFile("image", originalFileExtension);

        // Transfere o MultipartFile para o arquivo temporário
        image.transferTo(tempFile);
        String imageUrl = imageService.uploadImage(tempFile, bucketName);

        if (animalRequestDTO.name() == null || animalRequestDTO.speciesId() == null) {
            throw new InvalidRequestException("Nome ou espécie não informados.");
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
        Specie species = specieService.getSpecieById(speciesId);

        Optional.ofNullable(species).orElseThrow(() -> new NotFoundException("Informe uma espécie válida."));

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

        Optional.ofNullable(animal).orElseThrow(() -> new NotFoundException("Animal"));

        animalUpdateDTO.name().ifPresent(animal::setName);
        animalUpdateDTO.age().ifPresent(animal::setAge);

        if (animalUpdateDTO.species().isPresent()) {
            String stringId = animalUpdateDTO.species().get();
            UUID speciesId = UUID.fromString(stringId);
            Specie species = specieService.getSpecieById(speciesId);
            if (species == null) {
                throw new NotFoundException("Espécie");
            }
            animal.setSpecies(species);
        }

        animal = animalService.updateAnimal(animal);
        return new AnimalResponseDTO(animal);
    }

    public AnimalResponseDTO updateAnimalImage(UUID id, MultipartFile image) throws Exception {
        Animal animal = animalService.findAnimalById(id);

        Optional.ofNullable(animal).orElseThrow(() -> new NotFoundException("Animal"));

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
        animalService.deleteAnimal(id);
    }
}