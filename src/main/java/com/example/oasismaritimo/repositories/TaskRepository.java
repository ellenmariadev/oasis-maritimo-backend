package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
