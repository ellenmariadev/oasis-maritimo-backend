package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.user.AuthenticationDTO;
import com.example.oasismaritimo.domain.dto.user.LoginResponseDTO;
import com.example.oasismaritimo.domain.dto.user.RegisterDTO;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.domain.enums.UserRole;
import com.example.oasismaritimo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class AuthenticationFacadeTest {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testLogin() {
        String encryptedPassword = new BCryptPasswordEncoder().encode("testPassword");
        User testUser = new User("testUser", encryptedPassword, UserRole.ADMIN);
        userRepository.save(testUser);
        AuthenticationDTO authData = new AuthenticationDTO("testUser", "testPassword");

        ResponseEntity<LoginResponseDTO> response = authenticationFacade.login(authData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testUser", response.getBody().username());
        assertEquals(UserRole.ADMIN, response.getBody().role());
    }

    @Test
    void testRegister() {
        RegisterDTO registerData = new RegisterDTO("newUser", "newPassword", UserRole.ADMIN);

        ResponseEntity<RegisterDTO> response = authenticationFacade.register(registerData);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserDetails registeredUser = userRepository.findByLogin("newUser");

        assertNotNull(registeredUser);
        assertEquals(registerData.login(), registeredUser.getUsername());
        assertTrue(new BCryptPasswordEncoder().matches(registerData.password(), registeredUser.getPassword()));
    }
}