package com.tasks.Task.Tracker.domain.services.impl;

import com.tasks.Task.Tracker.domain.dtos.TaskDTO;
import com.tasks.Task.Tracker.domain.entities.Task;
import com.tasks.Task.Tracker.domain.entities.TaskList;
import com.tasks.Task.Tracker.domain.entities.TaskPriority;
import com.tasks.Task.Tracker.domain.entities.TaskStatus;
import com.tasks.Task.Tracker.domain.mappers.TaskMapper;
import com.tasks.Task.Tracker.domain.repository.TaskListRepository;
import com.tasks.Task.Tracker.domain.repository.TaskRepository;
import com.tasks.Task.Tracker.domain.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final TaskMapper taskMapper;
    @Override
    public List<TaskDTO> getAllTasks(Long taskListId) {
        List<Task> taskList = taskRepository.findByTaskListTaskListId(taskListId);
        if(taskList.isEmpty()){
            throw new IllegalArgumentException("Task list is empty");
        }
        return taskList.stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO, Long taskListId) {

        System.out.println("Salam");
        Task task = taskMapper.toEntity(taskDTO);

        if (task.getTaskId()!=null){
            throw new IllegalArgumentException("Task already has an ID");
        }

        if (task.getTitle()==null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title must be present");
        }

        Task newTask=new Task();

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found with ID " + taskListId));

        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setTaskList(taskList);
        newTask.setTaskStatus(TaskStatus.OPEN);
        newTask.setTaskPriority(TaskPriority.HIGH);
        newTask.setCreated(LocalDateTime.now());
        newTask.setUpdated(LocalDateTime.now());
        newTask.setDueDate(task.getDueDate());

        return taskMapper.toDto(taskRepository.save(newTask));
    }

    @Override
    public TaskDTO getTaskById(Long taskListId, Long taskId) {

        Task task = taskRepository.findByTaskListTaskListIdAndTaskId(taskListId, taskId).orElseThrow(
                () -> new IllegalArgumentException("Task not found ")
        );
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDTO updateTask(Long taskListId, Long taskId, TaskDTO taskDTO) {

        Task taskFromDb = taskRepository.findByTaskListTaskListIdAndTaskId(taskListId, taskId).orElseThrow(
                () -> new IllegalArgumentException("Task not found ")
        );

        if (!taskFromDb.getTaskList().getTaskListId().equals(taskListId)){
            throw new IllegalArgumentException("This task does not belong to this task list.");
        }

        taskFromDb.setTaskPriority(taskDTO.taskPriority());
        taskFromDb.setTaskStatus(taskDTO.taskStatus());
        taskFromDb.setTitle(taskDTO.title());
        taskFromDb.setDescription(taskDTO.description());
        taskFromDb.setUpdated(LocalDateTime.now());
        taskFromDb.setDueDate(taskDTO.dueDate());

        return taskMapper.toDto(taskRepository.save(taskFromDb));
    }

    @Override
    public String deleteTask(Long taskListId, Long taskId) {

        Task taskFromDb = taskRepository.findByTaskListTaskListIdAndTaskId(taskListId, taskId).orElseThrow(
                () -> new IllegalArgumentException("Task not found ")
        );
taskRepository.delete(taskFromDb);
        return "Task successfully deleted with Id "+taskId;
    }
}
