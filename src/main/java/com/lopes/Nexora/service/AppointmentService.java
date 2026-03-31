package com.lopes.Nexora.service;

import com.lopes.Nexora.dto.AppointmentRequestDTO;
import com.lopes.Nexora.dto.AppointmentResponseDTO;
import com.lopes.Nexora.dto.AppointmentUpdateDTO;
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

    public AppointmentResponseDTO saveAppointment(AppointmentRequestDTO dto){
        Appointment appointment = new Appointment(dto);
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
        Appointment saved = appointmentRepository.save(appointment);
        return new AppointmentResponseDTO(saved);
    }

    public void deleteByClient(LocalDateTime scheduledDateTime, String client){
        appointmentRepository.deleteByClientAndScheduledDateTime(client, scheduledDateTime);
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentResponseDTO::new)
                .toList();
    }

    public List<AppointmentResponseDTO> getAppointmentsByDay(LocalDate date){
        LocalDateTime startTimeDay = date.atStartOfDay();
        LocalDateTime endTimeDay = date.atTime(23,59,59);
        List<Appointment> entities = appointmentRepository.findByScheduledDateTimeBetween(startTimeDay, endTimeDay);
        return entities
                .stream()
                .map(AppointmentResponseDTO::new)
                .toList();
    }

    public AppointmentResponseDTO updateAppointment(AppointmentUpdateDTO dto, String client, LocalDateTime scheduledDateTime){
        Appointment existing = appointmentRepository.findByClientAndScheduledDateTime(client, scheduledDateTime)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        if (dto.scheduledDateTime().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Cannot reschedule to past");
        }

        LocalDateTime newStart = dto.scheduledDateTime();
        LocalDateTime newEnd = newStart.plusHours(1);
        Appointment conflict = appointmentRepository.findByServiceAndScheduledDateTimeBetween(
                dto.service(), newStart, newEnd);

        if (conflict != null && !conflict.getId().equals(existing.getId())) {
            throw new IllegalArgumentException("Slot busy for this service");
        }

        existing.setService(dto.service());
        existing.setProfessional(dto.professional());
        existing.setScheduledDateTime(dto.scheduledDateTime());

        Appointment saved = appointmentRepository.save(existing);
        return new AppointmentResponseDTO(saved);
    }
}
