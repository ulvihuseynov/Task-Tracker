package com.tasks.Task.Tracker.domain.controller;

import com.tasks.Task.Tracker.domain.dtos.TaskDTO;
import com.tasks.Task.Tracker.domain.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-list/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{taskListId}")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable Long taskListId){

       List<TaskDTO> taskDTOList= taskService.getAllTasks(taskListId);
       return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
    }

    @PostMapping("/{taskListId}")
    public ResponseEntity<TaskDTO>createTask(@RequestBody TaskDTO taskDTO,
            @PathVariable Long taskListId){
     TaskDTO savedTaskDTO= taskService.createTask(taskDTO,taskListId);
        return new ResponseEntity<>(savedTaskDTO, HttpStatus.OK);
    }

    @GetMapping("/{taskListId}/{taskId}")
    public ResponseEntity<TaskDTO>getTaskById(@PathVariable Long taskId,
                                             @PathVariable Long taskListId){
        TaskDTO savedTaskDTO= taskService.getTaskById(taskListId,taskId);
        return new ResponseEntity<>(savedTaskDTO, HttpStatus.OK);
    }

    @PutMapping("/{taskListId}/{taskId}")
    public ResponseEntity<TaskDTO>updateTask(@PathVariable Long taskId,
                                              @PathVariable Long taskListId,
                                             @RequestBody TaskDTO taskDTO){

        TaskDTO savedTaskDTO= taskService.updateTask(taskListId,taskId,taskDTO);

        return new ResponseEntity<>(savedTaskDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{taskListId}/{taskId}")
    public ResponseEntity<String>deleteTask(@PathVariable Long taskId,
                                             @PathVariable Long taskListId){

        String status= taskService.deleteTask(taskListId,taskId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
