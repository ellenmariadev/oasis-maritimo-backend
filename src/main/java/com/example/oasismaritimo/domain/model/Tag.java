package com.example.oasismaritimo.domain.model;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table (name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public Tag(){}

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
}
