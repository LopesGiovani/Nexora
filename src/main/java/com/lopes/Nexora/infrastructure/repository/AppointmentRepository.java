package com.lopes.Nexora.infrastructure.repository;

import com.lopes.Nexora.infrastructure.entity.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByServiceAndScheduledDateTimeBetween(String service, LocalDateTime startTime,
                                                         LocalDateTime endTime);

    List<Appointment> findByScheduledDateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    Optional<Appointment> findByClientAndScheduledDateTime(String client, LocalDateTime date);

    @Transactional
    void deleteByClientAndScheduledDateTime(String client, LocalDateTime date);

}