package com.example.oasismaritimo.domain.model;

import com.example.oasismaritimo.domain.dto.annotation.AnnotationRequestDTO;
import com.example.oasismaritimo.domain.dto.task.TaskRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "annotation")
public class Annotation {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToMany()
    @JoinTable(
            name = "annotation_tags",
            joinColumns = @JoinColumn(name = "annotation_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Annotation() {}

    public Annotation(AnnotationRequestDTO annotationRequestDTODTO, User author, Animal animal, Set<Tag> tags) {
        this.title = annotationRequestDTODTO.title();
        this.description = annotationRequestDTODTO.description();
        this.tags = tags;
        this.author = author;
        this.animal = animal;
        this.createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public UUID getId() {
        return id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
