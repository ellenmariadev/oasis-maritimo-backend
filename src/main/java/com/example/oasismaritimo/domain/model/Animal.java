package com.example.oasismaritimo.domain.model;
import com.example.oasismaritimo.domain.dto.animal.AnimalRequestDTO;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "species", referencedColumnName = "id")
    private Specie species;
    private int age;
    private Date arrivalDate;
    private String diet;
    private double weight;
    private double length;
    private String habitat;
    private String imageUrl;

    public Animal() {

    }

    public Animal(AnimalRequestDTO animalRequestDTO, Specie species, String imageUrl) {
        this.name = animalRequestDTO.name();
        this.species = species;
        this.age = animalRequestDTO.age();
        this.weight = animalRequestDTO.weight();
        this.length = animalRequestDTO.length();
        this.habitat = animalRequestDTO.habitat();
        this.arrivalDate = animalRequestDTO.arrivalDate();
        this.diet = animalRequestDTO.diet();
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specie getSpecies() {
        return species;
    }

    public void setSpecies(Specie species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
