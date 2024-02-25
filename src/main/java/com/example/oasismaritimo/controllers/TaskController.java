package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.task.TaskRequestDTO;
import com.example.oasismaritimo.domain.dto.task.TaskResponseDTO;
import com.example.oasismaritimo.domain.dto.task.TaskUpdateDTO;
import com.example.oasismaritimo.facade.TaskFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskFacade taskFacade;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(taskFacade.getAllTasks());
    }

    @Autowired
    public TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.ok(taskFacade.createTask(taskRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(taskFacade.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable("id") UUID id, @RequestBody TaskUpdateDTO taskRequestDTO) {
        return ResponseEntity.ok(taskFacade.updateTask(id, taskRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") UUID id) {
        taskFacade.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}