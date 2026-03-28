package com.lopes.Nexora.dto;

import com.lopes.Nexora.infrastructure.entity.Appointment;

import java.time.LocalDateTime;
public record AppointmentResponseDTO(
        Long id,
        String service,
        String professional,
        LocalDateTime scheduledDateTime,
        String client
) {
    public AppointmentResponseDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getService(),
                appointment.getProfessional(),
                appointment.getScheduledDateTime(),
                appointment.getClient()
        );
    }
}