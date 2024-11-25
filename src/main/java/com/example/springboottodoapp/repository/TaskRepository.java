package com.example.springboottodoapp.repository;

import com.example.springboottodoapp.entity.Task;
import com.example.springboottodoapp.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<User> findByUser(User user);

    List<Task> findAllByUser(User user);

}
