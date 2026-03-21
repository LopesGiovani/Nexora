package com.lopes.Nexora.service;

import com.lopes.Nexora.infrastructure.entity.Appointment;
import com.lopes.Nexora.infrastructure.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment){
        LocalDateTime startTime = appointment.getScheduledDateTime();
        LocalDateTime endTime = startTime.plusHours(1);

        if (startTime.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Appointment cannot be in the past");
        }

        Appointment scheduled = appointmentRepository.findByServiceAndScheduledDateTimeBetween(
                appointment.getService(),
                startTime,
                endTime);

        if (Objects.nonNull(scheduled)){
            throw new IllegalArgumentException("Slot busy for this service");
        }

        return appointmentRepository.save(appointment);
    }

    public void deleteByClient(LocalDateTime scheduledDateTime, String client){
        appointmentRepository.deleteByClientAndScheduledDateTime(client, scheduledDateTime);
    }

    public List<Appointment> getAppointmentsByDay(LocalDate date){
        LocalDateTime startTimeDay = date.atStartOfDay();
        LocalDateTime endTimeDay = date.atTime(23,59,59);
        return appointmentRepository.findByScheduledDateTimeBetween(startTimeDay, endTimeDay);
    }

    public Appointment updateAppointment(Appointment updatedAppointment, String client, LocalDateTime scheduledDateTime){
        Appointment existing = appointmentRepository.findByClientAndScheduledDateTime(client, scheduledDateTime)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (updatedAppointment.getScheduledDateTime().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Cannot reschedule to past");
        }

        updatedAppointment.setId(existing.getId());
        return appointmentRepository.save(updatedAppointment);
    }
}
