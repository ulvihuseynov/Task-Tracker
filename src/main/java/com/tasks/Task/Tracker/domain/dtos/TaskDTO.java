package com.tasks.Task.Tracker.domain.dtos;

import com.tasks.Task.Tracker.domain.entities.TaskPriority;
import com.tasks.Task.Tracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;

public record TaskDTO(
        Long taskId,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus
) {
}
