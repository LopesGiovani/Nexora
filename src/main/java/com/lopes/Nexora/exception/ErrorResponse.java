package com.lopes.Nexora.exception;

public record ErrorResponse(
        String error,
        String message,
        int status,
        String timestamp,
        String path
) {}
