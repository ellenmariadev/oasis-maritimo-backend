package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.appointment.AppointmentRequestDTO;
import com.example.oasismaritimo.domain.dto.appointment.AppointmentResponseDTO;
import com.example.oasismaritimo.domain.dto.appointment.AppointmentUpdateDTO;
import com.example.oasismaritimo.facade.AppointmentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentFacade appointmentFacade;

    @Autowired
    public AppointmentController(AppointmentFacade appointmentFacade) {
        this.appointmentFacade = appointmentFacade;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentFacade.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(appointmentFacade.getAppointmentById(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return ResponseEntity.ok(appointmentFacade.createAppointment(appointmentRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable UUID id, @RequestBody AppointmentUpdateDTO appointmentUpdateDTO) {
        return ResponseEntity.ok(appointmentFacade.updateAppointment(id, appointmentUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        appointmentFacade.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}