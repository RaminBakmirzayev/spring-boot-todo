package com.example.springboottodoapp.mapper;

import com.example.springboottodoapp.dto.TaskRequest;
import com.example.springboottodoapp.dto.TaskResponse;
import com.example.springboottodoapp.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task mapToTask(TaskRequest taskRequest);

    List<TaskResponse> mapToTaskResponseList(List<Task> userTasks);

    TaskResponse mapToTaskResponse(Task task);
}
