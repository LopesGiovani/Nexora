package com.lopes.Nexora.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
    @NotBlank
    String service,

    @NotBlank
    String professional,

    @NotNull
    @Future
    LocalDateTime scheduledDateTime
) {
}
