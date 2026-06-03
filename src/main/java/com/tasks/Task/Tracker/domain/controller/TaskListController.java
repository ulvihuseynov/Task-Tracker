package com.tasks.Task.Tracker.domain.controller;

import com.tasks.Task.Tracker.domain.dtos.TaskListDTO;
import com.tasks.Task.Tracker.domain.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    @GetMapping
    public ResponseEntity<List<TaskListDTO>> getTaskLists(){
       return new ResponseEntity<>(taskListService.getTaskList(), HttpStatus.OK);
    }

    @GetMapping("/{taskListId}")
    public ResponseEntity<TaskListDTO> getTaskListById(@PathVariable Long taskListId){
        return new ResponseEntity<>(taskListService.getTaskListById(taskListId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskListDTO> createTaskLists(@RequestBody TaskListDTO taskListDTO){
        return new ResponseEntity<>(taskListService.createTaskLists(taskListDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{taskListId}")
    public ResponseEntity<TaskListDTO> updateTaskList(@RequestBody TaskListDTO taskListDTO,
                                                      @PathVariable Long taskListId){
            return new ResponseEntity<>(taskListService.updateTaskList(taskListDTO,taskListId), HttpStatus.OK);
    }

    @DeleteMapping("/{taskListId}")
    public ResponseEntity<String> deleteTaskList(@PathVariable Long taskListId){
        return new ResponseEntity<>(taskListService.deleteTaskList(taskListId), HttpStatus.OK);
    }
}
