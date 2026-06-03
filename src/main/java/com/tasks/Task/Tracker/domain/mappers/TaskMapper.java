package com.tasks.Task.Tracker.domain.mappers;

import com.tasks.Task.Tracker.domain.dtos.TaskDTO;
import com.tasks.Task.Tracker.domain.entities.Task;

public interface TaskMapper {

    Task toEntity (TaskDTO taskDTO);
    TaskDTO toDto(Task task);
}
