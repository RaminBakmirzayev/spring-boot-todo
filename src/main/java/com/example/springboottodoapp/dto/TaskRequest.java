package com.example.springboottodoapp.dto;

import com.example.springboottodoapp.enums.STATUS;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskRequest {
    private String name;
    private STATUS status;

}
