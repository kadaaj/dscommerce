package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Email não encontrado"));

        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = repository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Email não encontrado"));

        return new UserDTO(user);
    }
}