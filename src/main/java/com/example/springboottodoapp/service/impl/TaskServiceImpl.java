package com.example.springboottodoapp.service.impl;

import com.example.springboottodoapp.dto.TaskRequest;
import com.example.springboottodoapp.dto.TaskResponse;
import com.example.springboottodoapp.entity.Task;
import com.example.springboottodoapp.entity.User;
import com.example.springboottodoapp.mapper.TaskMapper;
import com.example.springboottodoapp.mapper.UserMap;
import com.example.springboottodoapp.repository.TaskRepository;
import com.example.springboottodoapp.repository.UserRepository;
import com.example.springboottodoapp.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

@Log4j2

public class TaskServiceImpl implements TaskService {


    private final UserRepository userRepository;
    private final TaskRepository taskRepository;




    private final TaskMapper taskMapper = TaskMapper.INSTANCE;

    @Override
    public void createTask(TaskRequest taskRequest) {
        User currentUser = getCurrentUser();
        Task task = taskMapper.mapToTask(taskRequest);
        if (currentUser != null) {
            task.setUser(currentUser);
            taskRepository.save(task);
            log.info("Task created successfully for user: {}", currentUser.getEmail());
        }

    }

    @Override

    public List<TaskResponse> taskResponseList() {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            List<Task> userTasks = taskRepository.findAllByUser(currentUser);
            return taskMapper.mapToTaskResponseList(userTasks);
        }
        return null;

    }

    @Override
    public TaskResponse getTaskById(Long id) {
       Task task = taskRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Task not found"));
       return taskMapper.mapToTaskResponse(task);
    }

    @Override
    public void deleteTask(Long id) {
    taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(Long id,TaskRequest taskRequest) {
    Task task=taskRepository.findById(id).orElseThrow(()->new  UsernameNotFoundException("Task not found"));
    task.setStatus(taskRequest.getStatus());
    taskRepository.save(task);
    }


    private User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            log.info("Current user: {}", email);
            return userRepository.findByEmail(email).orElse(null);
        } else {
            return null;
        }
    }
}
