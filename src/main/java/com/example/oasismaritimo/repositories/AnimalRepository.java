package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID>{
    Animal findAnimalById(UUID id);
}
