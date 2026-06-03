package com.tasks.Task.Tracker.domain.dtos;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
