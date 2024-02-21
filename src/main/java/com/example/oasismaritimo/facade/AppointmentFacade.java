package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.appointment.AppointmentRequestDTO;
import com.example.oasismaritimo.domain.dto.appointment.AppointmentResponseDTO;
import com.example.oasismaritimo.domain.dto.appointment.AppointmentUpdateDTO;
import com.example.oasismaritimo.domain.model.Appointment;
import com.example.oasismaritimo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AppointmentFacade {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentFacade(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentService.getAllAppointments().stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public AppointmentResponseDTO getAppointmentById(UUID id) {
        return new AppointmentResponseDTO(appointmentService.getAppointmentById(id));
    }

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = appointmentService.createAppointment(appointmentRequestDTO);
        return new AppointmentResponseDTO(appointment);
    }

    public AppointmentResponseDTO updateAppointment(UUID id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentService.updateAppointment(id, appointmentUpdateDTO);
        return new AppointmentResponseDTO(appointment);
    }


    public void deleteAppointment(UUID id) {
        appointmentService.deleteAppointment(id);
    }
}