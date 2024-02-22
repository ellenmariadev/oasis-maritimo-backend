package com.example.oasismaritimo.domain.dto.animal;

import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Specie;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public record AnimalResponseDTO(
        UUID id,
        String name,
        Integer age,
        Specie specie,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date arrivalDate,
        String diet,
        Double weight,
        Double length,
        String habitat,
        String imageUrl
) {
    public AnimalResponseDTO(Animal animal){
        this(
                animal.getId(),
                animal.getName(),
                animal.getAge(),
                animal.getSpecies(),
                animal.getArrivalDate(),
                animal.getDiet(),
                animal.getWeight(),
                animal.getLength(),
                animal.getHabitat(),
                animal.getImageUrl()
        );
    }
}
