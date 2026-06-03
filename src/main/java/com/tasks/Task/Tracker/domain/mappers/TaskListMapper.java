package com.tasks.Task.Tracker.domain.mappers;

import com.tasks.Task.Tracker.domain.dtos.TaskListDTO;
import com.tasks.Task.Tracker.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList toEntity (TaskListDTO taskListDTO);

    TaskListDTO toDto(TaskList taskList);
}
