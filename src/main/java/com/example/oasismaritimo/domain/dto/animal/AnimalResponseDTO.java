package com.example.oasismaritimo.domain.dto.animal;

import com.example.oasismaritimo.domain.model.Animal;

public record AnimalResponseDTO(int id, String name, int age ) {
    public AnimalResponseDTO(Animal animal){
        this(animal.getId(), animal.getName(), animal.getAge());
    }
}
