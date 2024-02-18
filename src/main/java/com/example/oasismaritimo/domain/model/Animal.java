package com.example.oasismaritimo.domain.model;

import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import jakarta.persistence.*;

@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String species;
    private int age;

    public Animal() {

    }

    public Animal(AnimalRequestDTO data) {
        this.name = data.name();
        this.species = data.species();
        this.age = data.age();
    }

    public int getId() {
        return id;
    }

    public void setId(int uid) {
        this.id = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}