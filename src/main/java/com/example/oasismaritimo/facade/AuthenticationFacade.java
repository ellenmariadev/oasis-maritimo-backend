package com.example.oasismaritimo.facade;

import com.example.oasismaritimo.domain.dto.user.AuthenticationDTO;
import com.example.oasismaritimo.domain.dto.user.LoginResponseDTO;
import com.example.oasismaritimo.domain.dto.user.RegisterDTO;
import com.example.oasismaritimo.domain.model.User;
import com.example.oasismaritimo.infra.auth.TokenService;
import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;


    /**
     * Autentica um usuário e gera um token.
     *
     * @param data um objeto AuthenticationDTO contendo login e senha do usuário.
     * @return uma resposta contendo o token de acesso do usuário autenticado.
     */
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token, data.login(), user.getRole(), user.getId()));
    }

    /**
     * Cria um novo usuário.
     *
     * @param data um objeto RegisterDTO contendo login, senha e papel do novo usuário.
     * @return uma resposta indicando o sucesso ou falha da criação do usuário.
     */
    public ResponseEntity<RegisterDTO> register(RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}