package com.tasks.Task.Tracker.domain.services;

import com.tasks.Task.Tracker.domain.dtos.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks(Long taskListId);

    TaskDTO createTask(TaskDTO taskDTO, Long taskListId);

    TaskDTO getTaskById(Long taskListId, Long taskId);

    TaskDTO updateTask(Long taskListId, Long taskId,TaskDTO taskDTO);

    String deleteTask(Long taskListId, Long taskId);
}
