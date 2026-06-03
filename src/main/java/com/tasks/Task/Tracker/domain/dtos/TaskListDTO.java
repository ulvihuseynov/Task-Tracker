package com.tasks.Task.Tracker.domain.dtos;

import java.util.List;

public record TaskListDTO(
        Long taskListId,
        String title,
        String description,
       Integer count,
       Double progress,
        List<TaskDTO> tasks
) {
}
