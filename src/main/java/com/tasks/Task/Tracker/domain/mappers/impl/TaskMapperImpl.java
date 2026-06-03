package com.tasks.Task.Tracker.domain.mappers.impl;

import com.tasks.Task.Tracker.domain.dtos.TaskDTO;
import com.tasks.Task.Tracker.domain.entities.Task;
import com.tasks.Task.Tracker.domain.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task toEntity(TaskDTO taskDTO) {
       return
               new Task(
                       taskDTO.taskId(),
                       taskDTO.title(),
                       taskDTO.description(),
                        taskDTO.dueDate(),
                       taskDTO.taskStatus(),
                       taskDTO.taskPriority(),
                       null,
                       null,
                       null
               );
    }

    @Override
    public TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getTaskStatus()
        );
    }
}
