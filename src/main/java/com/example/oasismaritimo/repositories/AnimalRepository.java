package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{
    Animal findByName(String name);
}
