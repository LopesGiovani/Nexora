package com.lopes.Nexora.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegistrationDTO(@NotBlank String login, @NotBlank String password) {
}
