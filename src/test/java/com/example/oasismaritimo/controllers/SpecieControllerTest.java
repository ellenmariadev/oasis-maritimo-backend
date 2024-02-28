package com.example.oasismaritimo.controllers;

import com.example.oasismaritimo.domain.dto.user.AuthenticationDTO;
import com.example.oasismaritimo.domain.dto.user.LoginResponseDTO;
import com.example.oasismaritimo.domain.model.Specie;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.domain.enums.UserRole;
import com.example.oasismaritimo.facade.AuthenticationFacade;
import com.example.oasismaritimo.repositories.SpecieRepository;
import com.example.oasismaritimo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SpecieControllerTest {

    @Autowired
    SpecieRepository specieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    TestRestTemplate restTemplate;

    UUID specieId;

    String token;

    @BeforeAll
    void setUp() {
        Specie specie = new Specie("Test Specie");
        Specie savedSpecie = specieRepository.save(specie);
        specieId = savedSpecie.getId();

        User testUser = new User();
        testUser.setLogin("testUser");
        String encryptedPassword = new BCryptPasswordEncoder().encode("12345678");
        testUser.setPassword(encryptedPassword);
        testUser.setRole(UserRole.ADMIN);

        userRepository.save(testUser);

        // Autenticar o usuário e pegar o token
        AuthenticationDTO authData = new AuthenticationDTO(testUser.getLogin(), "12345678");
        ResponseEntity<LoginResponseDTO> loginResponse = authenticationFacade.login(authData);
        token = Objects.requireNonNull(loginResponse.getBody()).token();
    }

    @Test
    // GET {id} /api/v1/species/{id}
    void shouldFindSpecieById() {
        ResponseEntity<Specie> response = restTemplate.exchange("/api/v1/species/" + specieId, HttpMethod.GET, null, Specie.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    // POST /api/v1/species
    void shouldCreateSpecie() {

        Specie specie = new Specie("New Specie");

        // Criar os headers com o token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        // Criar a requisição com o token
        HttpEntity<Specie> request = new HttpEntity<>(specie, headers);

        ResponseEntity<Specie> response = restTemplate.exchange("/api/v1/species", HttpMethod.POST, request, Specie.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }


    @Test
    @Rollback
    // UPDATE {id} /api/v1/species/{id}
    void shouldUpdateSpecie() {
        ResponseEntity<Specie> response = restTemplate.exchange("/api/v1/species/" + specieId, HttpMethod.GET, null, Specie.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Specie existing = response.getBody();
        assertThat(existing).isNotNull();
        Specie updated = new Specie("New Specie 01");

        assertThat(updated.getName()).isEqualTo("New Specie 01");
    }

    @Test
    @Rollback
    // DELETE {id} /api/v1/species/{id}
    void shouldDeleteSpecie() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/species/" + specieId, HttpMethod.DELETE, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }



}