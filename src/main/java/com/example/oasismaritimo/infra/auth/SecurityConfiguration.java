package com.example.oasismaritimo.infra.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * A classe SecurityConfiguration é responsável pela configuração do Spring Security.
 * Ela define as regras de autorização e autenticação da aplicação.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/animals").hasAnyRole("BIOLOGIST", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/animals/*").hasAnyRole("BIOLOGIST", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/animals/*").hasAnyRole("BIOLOGIST", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/species").hasAnyRole("BIOLOGIST", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/species/*").hasAnyRole("BIOLOGIST", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/tasks").hasAnyRole("CARETAKER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/tasks/*").hasAnyRole("CARETAKER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/tasks/*").hasAnyRole("CARETAKER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/tags/*").hasAnyRole("BIOLOGIST", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/tags/*").hasAnyRole("BIOLOGIST", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/annotations/*").hasAnyRole("BIOLOGIST", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/annotations/*").hasAnyRole("BIOLOGIST", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/annotations/*").hasAnyRole("BIOLOGIST", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/appointments/*").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/appointments/*").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointments/*").hasAnyRole("VETERINARIAN", "ADMIN")

                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

