package com.example.oasismaritimo.services;

import com.example.oasismaritimo.domain.dto.task.TaskRequestDTO;
import com.example.oasismaritimo.domain.dto.task.TaskResponseDTO;
import com.example.oasismaritimo.domain.dto.task.TaskUpdateDTO;
import com.example.oasismaritimo.domain.model.Animal;
import com.example.oasismaritimo.domain.model.Task;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.domain.enums.UserRole;
import com.example.oasismaritimo.exceptions.InvalidRequestException;
import com.example.oasismaritimo.exceptions.NotFoundException;
import com.example.oasismaritimo.repositories.AnimalRepository;
import com.example.oasismaritimo.repositories.TaskRepository;
import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, AnimalRepository animalRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        User author = null;
        Animal animal = null;
        if (taskRequestDTO.authorId() != null) {
            author = userRepository.findById(taskRequestDTO.authorId()).orElse(null);
            // A task só pode ser atribuída a um cuidador
            if (author != null && !author.getRole().equals(UserRole.CARETAKER)) {
                throw new InvalidRequestException("O autor da tarefa deve ser um cuidador.");
            }
        }
        if (taskRequestDTO.animalId() != null) {
            animal = animalRepository.findById(taskRequestDTO.animalId()).orElse(null);
        }
        Task task = new Task(taskRequestDTO, author, animal);

        task = taskRepository.save(task);
        return new TaskResponseDTO(task);
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Tarefa"));
    }

    public TaskResponseDTO updateTask(UUID id, TaskUpdateDTO taskUpdateDTO) {
        Task task = getTaskById(id);

        taskUpdateDTO.title().ifPresent(task::setTitle);
        taskUpdateDTO.description().ifPresent(task::setDescription);
        taskUpdateDTO.status().ifPresent(task::setStatus);

        Task authorTask = task;
        taskUpdateDTO.authorId().ifPresent(authorId -> {
            User author = userRepository.findById(authorId).orElse(null);
            if (author != null && author.getRole().equals(UserRole.CARETAKER)) {
                authorTask.setAuthor(author);
            } else {
                throw new InvalidRequestException("O autor da tarefa deve ser um cuidador.");
            }
        });

        Task animalTask = task;
        taskUpdateDTO.animalId().ifPresent(animalId -> {
            Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new NotFoundException("Animal"));
            if (animal != null) {
                animalTask.setAnimal(animal);
            }
        });

        task = taskRepository.save(task);
        return new TaskResponseDTO(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskResponseDTO::new).toList();
    }
}