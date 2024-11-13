package com.example.springboottodoapp.dto;

import com.example.springboottodoapp.enums.STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {
    private String name;
    private STATUS status;

}
