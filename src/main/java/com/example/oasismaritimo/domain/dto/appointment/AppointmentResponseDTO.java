package com.example.oasismaritimo.domain.dto.appointment;

import com.example.oasismaritimo.domain.enums.Status;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Appointment;
import com.example.oasismaritimo.domain.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public record AppointmentResponseDTO (
        UUID id,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date date,
        Time time,
        Status status,
        Animal animal,
        User veterinarian
) {
    public AppointmentResponseDTO(Appointment appointment) {
        this(appointment.getId(),
        appointment.getDescription(),
        appointment.getDate(),
        appointment.getTime(),
        appointment.getStatus(),
        appointment.getAnimal(),
        appointment.getVeterinarian()
        );
    }
}
