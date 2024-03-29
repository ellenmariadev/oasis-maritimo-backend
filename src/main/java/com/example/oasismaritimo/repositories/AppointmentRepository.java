package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}
