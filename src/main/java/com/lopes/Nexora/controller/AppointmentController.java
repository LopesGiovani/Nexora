package com.lopes.Nexora.controller;

import com.lopes.Nexora.dto.AppointmentRequestDTO;
import com.lopes.Nexora.dto.AppointmentResponseDTO;
import com.lopes.Nexora.dto.AppointmentUpdateDTO;
import com.lopes.Nexora.service.AppointmentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AppointmentResponseDTO> saveAppointment(@RequestBody @Valid AppointmentRequestDTO dto) {
        return ResponseEntity.ok().body(appointmentService.saveAppointment(dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAppointment(@RequestParam LocalDateTime scheduledDateTime,
                                                  @RequestParam String client) {
        appointmentService.deleteByClient(scheduledDateTime, client);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getByDay(
            @RequestParam(required = false) LocalDate date
    ) {
        var response = (date != null)
                ? appointmentService.getAppointmentsByDay(date)
                : appointmentService.getAllAppointments();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@RequestBody @Valid AppointmentUpdateDTO dto,
                                                                    @RequestParam String client,
                                                                    @RequestParam LocalDateTime scheduledDateTime) {
        return ResponseEntity.accepted().body(appointmentService
                .updateAppointment(dto, client, scheduledDateTime));
    }
}
