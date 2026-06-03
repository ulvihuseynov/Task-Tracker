package com.tasks.Task.Tracker.domain.services;

import com.tasks.Task.Tracker.domain.dtos.TaskListDTO;
import com.tasks.Task.Tracker.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskListDTO> getTaskList();

    TaskListDTO createTaskLists(TaskListDTO taskListDTO);

    TaskListDTO getTaskListById(Long taskListId);

    TaskListDTO updateTaskList(TaskListDTO taskListDTO, Long taskListId);
}
