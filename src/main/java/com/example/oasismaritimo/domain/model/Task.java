package com.example.oasismaritimo.domain.model;

import com.example.oasismaritimo.domain.dto.task.TaskRequestDTO;
import com.example.oasismaritimo.domain.enums.StatusTask;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private StatusTask status;
    private Date createdAt;

    public Task () {}

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    public Task(TaskRequestDTO taskRequestDTO, User author, Animal animal) {
        this.title = taskRequestDTO.title();
        this.description = taskRequestDTO.description();
        this.status = taskRequestDTO.status();
        this.author = author;
        this.animal = animal;
        this.createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}