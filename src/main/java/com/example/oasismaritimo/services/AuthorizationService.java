package com.example.oasismaritimo.services;

import com.example.oasismaritimo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * A classe AuthorizationService implementa a interface UserDetailsService do Spring Security, que é usada para recuperar as infos do usuário durante a autenticação.
 */
@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    /**
     * Este método é usado pelo Spring Security para autenticar o usuário durante o login.
     *
     * @param username o nome de usuário do usuário a ser autenticado
     * @return os detalhes do usuário
     * @throws UsernameNotFoundException se o usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
