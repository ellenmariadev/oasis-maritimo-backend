package com.example.oasismaritimo.domain.dto.appointment;

import com.example.oasismaritimo.domain.enums.StatusAppointment;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public record AppointmentRequestDTO(
    String description,
    @NotBlank
    Date date,
    @NotBlank
    Time time,
    StatusAppointment status,
    UUID animalId,
    UUID veterinarianId

) { }
