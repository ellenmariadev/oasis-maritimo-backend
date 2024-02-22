package com.example.oasismaritimo.repositories;

import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.domain.model.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should return an user by login successfully from database")
        void findByLoginCase1() {
            String login = "user";
            User data = new User(login, "12345678", UserRole.CARETAKER);
            this.createUser(data);

        UserDetails result = this.userRepository.findByLogin(login);
        assertThat(result.getUsername().equals(login));
    }

    private User createUser(User data) {
        User newUser = new User(data.getLogin(), data.getPassword(), data.getRole());
        this.entityManager.persist(newUser);
        return newUser;
    }
}