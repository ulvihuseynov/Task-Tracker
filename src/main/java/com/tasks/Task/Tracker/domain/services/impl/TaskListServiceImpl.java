package com.tasks.Task.Tracker.domain.services.impl;

import com.tasks.Task.Tracker.domain.dtos.TaskListDTO;
import com.tasks.Task.Tracker.domain.entities.TaskList;
import com.tasks.Task.Tracker.domain.mappers.TaskListMapper;
import com.tasks.Task.Tracker.domain.repository.TaskListRepository;
import com.tasks.Task.Tracker.domain.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;
    @Override
    public List<TaskListDTO> getTaskList() {
        List<TaskList> taskLists = taskListRepository.findAll();
if (taskLists.isEmpty()){
    throw new IllegalArgumentException("Task list is empty");
}
      return   taskLists.stream().map(taskListMapper::toDto).toList();
    }

    @Override
    public TaskListDTO createTaskLists(TaskListDTO taskListDTO) {
        TaskList taskList = taskListMapper.toEntity(taskListDTO);

        if (taskList.getTaskListId()!=null){
            throw new IllegalArgumentException("Task list already has an ID");
        }

        if (taskList.getTitle()==null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title must be present");
        }

        TaskList newTaskList=new TaskList();

        LocalDateTime now = LocalDateTime.now();

        newTaskList.setTitle(taskList.getTitle());
        newTaskList.setDescription(taskList.getDescription());
        newTaskList.setCreated(now);
        newTaskList.setUpdated(now);
        newTaskList.setTasks(taskList.getTasks());

        return taskListMapper.toDto(taskListRepository.save(newTaskList));
    }

    @Override
    public TaskListDTO getTaskListById(Long taskListId) {
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found with id " + taskListId));
        return taskListMapper.toDto(taskList);
    }

    @Override
    public TaskListDTO updateTaskList(TaskListDTO taskListDTO, Long taskListId) {

        TaskList taskListFromDb = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found with id " + taskListId));


        taskListFromDb.setTitle(taskListDTO.title());
        taskListFromDb.setDescription(taskListDTO.description());
        taskListFromDb.setUpdated( LocalDateTime.now());

        return taskListMapper.toDto(taskListRepository.save(taskListFromDb));
    }

    @Override
    public String deleteTaskList(Long taskListId) {

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found with id " + taskListId));
        taskListRepository.delete(taskList);
        return "Task list successfully with ID "+ taskListId;
    }
}
