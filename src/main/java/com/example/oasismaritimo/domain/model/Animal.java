package com.example.oasismaritimo.domain.model;
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

    public Animal() {

    }

    public Animal(String name, Specie species, int age, double weight, double length, String habitat, Date arrivalDate, String diet) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
        this.length = length;
        this.habitat = habitat;
        this.arrivalDate = arrivalDate;
        this.diet = diet;
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
