package com.example.rewards.model;

import java.time.LocalDateTime;

public record ErrorResponse(
        String error,
        int status,
        LocalDateTime timestamp
) {}
