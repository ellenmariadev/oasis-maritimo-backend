package com.example.oasismaritimo.domain.dto.appointment;

import com.example.oasismaritimo.domain.enums.StatusAppointment;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public record AppointmentUpdateDTO(
        Optional<String> description,
        Optional<Date> date,
        Optional<Time> time,
        Optional<StatusAppointment> status,
        Optional<UUID> animalId
) { }
