package com.example.springboottodoapp.service;

import com.example.springboottodoapp.dto.TaskRequest;
import com.example.springboottodoapp.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    void createTask(TaskRequest taskRequest);
    List<TaskResponse> taskResponseList();

    TaskResponse getTaskById(Long  id);

    void deleteTask(Long id);

    void updateTask(TaskRequest taskRequest);

}
