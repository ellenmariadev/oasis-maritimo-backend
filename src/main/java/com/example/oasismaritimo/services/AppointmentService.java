package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.appointment.AppointmentRequestDTO;
import com.example.oasismaritimo.domain.dto.appointment.AppointmentUpdateDTO;
import com.example.oasismaritimo.domain.model.*;
import com.example.oasismaritimo.exceptions.InvalidRequestException;
import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento"));
    }

    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        User author = null;
        Animal animal = null;
        if (appointmentRequestDTO.veterinarianId() != null) {
            author = userRepository.findById(appointmentRequestDTO.veterinarianId()).orElseThrow(() -> new InvalidRequestException("Informe o veterinário."));
        }
        if (appointmentRequestDTO.animalId() != null) {
            animal = animalRepository.findById(appointmentRequestDTO.animalId()).orElseThrow(() -> new InvalidRequestException("Informe o animal."));
        }
        Appointment appointment = new Appointment(appointmentRequestDTO, author, animal);
        appointment = appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment updateAppointment(UUID id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento"));
        appointmentUpdateDTO.description().ifPresent(appointment::setDescription);
        appointmentUpdateDTO.date().ifPresent(appointment::setDate);
        appointmentUpdateDTO.time().ifPresent(appointment::setTime);
        appointmentUpdateDTO.status().ifPresent(appointment::setStatus);

        appointmentUpdateDTO.animalId().ifPresent(animalId -> {
            Animal animal = animalRepository.findById(animalId)
                    .orElseThrow(() -> new NotFoundException("Animal"));
            appointment.setAnimal(animal);
        });

        return appointmentRepository.save(appointment);
    }


    public void deleteAppointment(UUID id) {
        Optional.ofNullable(getAppointmentById(id)).orElseThrow(() -> new NotFoundException("Agendamento"));
        appointmentRepository.deleteById(id);
    }
}