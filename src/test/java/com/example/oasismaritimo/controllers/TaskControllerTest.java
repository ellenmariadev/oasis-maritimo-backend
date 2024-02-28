package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.task.TaskResponseDTO;
import com.example.oasismaritimo.domain.dto.user.AuthenticationDTO;
import com.example.oasismaritimo.domain.dto.user.LoginResponseDTO;
import com.example.oasismaritimo.domain.enums.StatusTask;
import com.example.oasismaritimo.domain.enums.UserRole;
import com.example.oasismaritimo.domain.model.Task;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.facade.AuthenticationFacade;
import com.example.oasismaritimo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class TaskControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    TestRestTemplate restTemplate;

    String token;

    UUID userId;

    User author;

    @BeforeAll
    void setUp() {
        User testUser = new User();
        testUser.setLogin("testUser");
        String encryptedPassword = new BCryptPasswordEncoder().encode("12345678");
        testUser.setPassword(encryptedPassword);
        testUser.setRole(UserRole.ADMIN);

        userRepository.save(testUser);
        userId = testUser.getId();
        author = testUser;

        // Autenticar o usuário e pegar o token
        AuthenticationDTO authData = new AuthenticationDTO(testUser.getLogin(), "12345678");
        ResponseEntity<LoginResponseDTO> loginResponse = authenticationFacade.login(authData);
        token = Objects.requireNonNull(loginResponse.getBody()).token();
    }


    @Test
    void shouldCreateTask() {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(StatusTask.TODO);
        task.setAnimal(null);
        task.setAuthor(author);
        task.setCreatedAt(new Date());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Task> request = new HttpEntity<>(task, headers);

            ResponseEntity<TaskResponseDTO> response = restTemplate.exchange(
                    "/api/v1/tasks",
                    HttpMethod.POST,
                    request,
                    TaskResponseDTO.class
            );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().title()).isEqualTo("Test Task");
    }
}