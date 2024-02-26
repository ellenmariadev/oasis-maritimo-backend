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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
