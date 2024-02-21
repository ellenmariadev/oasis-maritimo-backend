package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.appointment.AppointmentRequestDTO;
import com.example.oasismaritimo.domain.dto.appointment.AppointmentUpdateDTO;
import com.example.oasismaritimo.domain.model.*;
import com.example.oasismaritimo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, AnimalRepository animalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        User author = null;
        Animal animal = null;
        if (appointmentRequestDTO.veterinarianId() != null) {
            author = userRepository.findById(appointmentRequestDTO.veterinarianId()).orElse(null);
        }
        if (appointmentRequestDTO.animalId() != null) {
            animal = animalRepository.findById(appointmentRequestDTO.animalId()).orElse(null);
        }
        Appointment appointment = new Appointment(appointmentRequestDTO, author, animal);
        appointment = appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment updateAppointment(UUID id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentUpdateDTO.description().ifPresent(appointment::setDescription);
        appointmentUpdateDTO.date().ifPresent(appointment::setDate);
        appointmentUpdateDTO.time().ifPresent(appointment::setTime);
        appointmentUpdateDTO.status().ifPresent(appointment::setStatus);

        appointmentUpdateDTO.animalId().ifPresent(animalId -> {
            Animal animal = animalRepository.findById(animalId)
                    .orElseThrow(() -> new RuntimeException("Animal not found"));
            appointment.setAnimal(animal);
        });

        return appointmentRepository.save(appointment);
    }


    public void deleteAppointment(UUID id) {
        appointmentRepository.deleteById(id);
    }
}