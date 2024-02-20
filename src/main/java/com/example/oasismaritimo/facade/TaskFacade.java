package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.task.TaskRequestDTO;
import com.example.oasismaritimo.domain.dto.task.TaskResponseDTO;
import com.example.oasismaritimo.domain.dto.task.TaskUpdateDTO;
import com.example.oasismaritimo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskFacade {

    private final TaskService taskService;

    @Autowired
    public TaskFacade(TaskService taskService) {
        this.taskService = taskService;
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(taskRequestDTO);
    }

    public TaskResponseDTO getTaskById(UUID id) {
        return new TaskResponseDTO(taskService.getTaskById(id));
    }

    public TaskResponseDTO updateTask(UUID id, TaskUpdateDTO taskRequestDTO) {
        return taskService.updateTask(id, taskRequestDTO);
    }

    public void deleteTask(UUID id) {
        taskService.deleteTask(id);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }
}