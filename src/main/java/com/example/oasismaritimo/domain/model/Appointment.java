package com.example.oasismaritimo.domain.model;

import com.example.oasismaritimo.domain.dto.appointment.AppointmentRequestDTO;
import com.example.oasismaritimo.domain.enums.Status;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Table (name="appointment")
public class Appointment {
    @Id
    @GeneratedValue
    private UUID id;
    private String description;
    private Date date;
    private Time time;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", referencedColumnName = "id")
    private User veterinarian;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Appointment ()  {}
    public Appointment(AppointmentRequestDTO appointmentRequestDTO, User veterinarian, Animal animal) {
        this.description = appointmentRequestDTO.description();
        this.date = appointmentRequestDTO.date();
        this.time = appointmentRequestDTO.time();
        this.status = Status.valueOf(appointmentRequestDTO.status());;
        this.animal = animal;
        this.veterinarian = veterinarian;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public User getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(User veterinarian) {
        this.veterinarian = veterinarian;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
