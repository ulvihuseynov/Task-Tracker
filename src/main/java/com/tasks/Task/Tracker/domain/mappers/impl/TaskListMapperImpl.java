package com.tasks.Task.Tracker.domain.mappers.impl;

import com.tasks.Task.Tracker.domain.dtos.TaskListDTO;
import com.tasks.Task.Tracker.domain.entities.Task;
import com.tasks.Task.Tracker.domain.entities.TaskList;
import com.tasks.Task.Tracker.domain.entities.TaskStatus;
import com.tasks.Task.Tracker.domain.mappers.TaskListMapper;
import com.tasks.Task.Tracker.domain.mappers.TaskMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    @Override
    public TaskList toEntity(TaskListDTO taskListDTO) {


        return new TaskList(
                taskListDTO.taskListId(),
                taskListDTO.title(),
                taskListDTO.description(),
                null,
                null,
                Optional.ofNullable(taskListDTO.tasks())
                        .map(taskDTOList -> taskDTOList
                                .stream().map(taskMapper::toEntity
                                        ).toList()).orElse(null)
                );
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        return new TaskListDTO(
                taskList.getTaskListId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
              Optional.ofNullable(taskList.getTasks())
                      .map(tasks ->tasks
                              .stream().map(taskMapper::toDto).toList())
                      .orElse(null)

        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if (null==tasks){
            return null;
        }
        long closedTaskCount=tasks.stream().filter(task -> TaskStatus.CLOSED==task.getTaskStatus())
                .count();

        return (double) closedTaskCount/tasks.size();
    }
}
