package com.example.oasismaritimo.repository;

import com.example.oasismaritimo.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{
    Animal findByName(String name);
}
