package com.example.springboottodoapp.entity;

import com.example.springboottodoapp.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "taskName", length = 256, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private STATUS status;
}
