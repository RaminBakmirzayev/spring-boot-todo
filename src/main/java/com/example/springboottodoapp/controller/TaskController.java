package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.dto.TaskRequest;
import com.example.springboottodoapp.dto.TaskResponse;

import com.example.springboottodoapp.service.TaskService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/tasks")
public class TaskController {
   private final  TaskService taskService;
   @PostMapping()                                   //Creating a new task for current User
    public ResponseEntity<Void>  createTask(@RequestBody TaskRequest taskRequest){
     taskService.createTask(taskRequest);
     return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> getUserTasks(){          // extracting tasks for current User
        List<TaskResponse> taskResponseList=taskService.taskResponseList();
        return ResponseEntity.ok(taskResponseList);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){          // updating a task for current User
        taskService.updateTask(id,taskRequest);                                                                   //The reason that I have used @RequestBody is that I can update either task name or task Status depends on what is required.
        return ResponseEntity.ok().build();
    }







}
