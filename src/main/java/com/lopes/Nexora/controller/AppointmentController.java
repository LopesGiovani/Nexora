package com.lopes.Nexora.controller;

import com.lopes.Nexora.infrastructure.entity.Appointment;
import com.lopes.Nexora.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment){
        return ResponseEntity.ok().body(appointmentService.saveAppointment(appointment));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAppointment(@RequestParam LocalDateTime scheduledDateTime,
                                                  @RequestParam String client){
        appointmentService.deleteByClient(scheduledDateTime, client);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getByDay(@RequestParam LocalDate date){
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByDay(date));
    }

    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment,
                                                         @RequestParam String client,
                                                         @RequestParam LocalDateTime scheduledDateTime){
        return ResponseEntity.accepted().body(appointmentService
                .updateAppointment(appointment, client, scheduledDateTime));
    }
}
