package com.devsuperior.dscommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth

                // Consultas de produtos são públicas
                .requestMatchers(HttpMethod.GET, "/products", "/products/**")
                .permitAll()

                // Cadastro de produto somente ADMIN
                .requestMatchers(HttpMethod.POST, "/products", "/products/**")
                .hasRole("ADMIN")

                // Alteração de produto somente ADMIN
                .requestMatchers(HttpMethod.PUT, "/products/**")
                .hasRole("ADMIN")

                // Exclusão de produto somente ADMIN
                .requestMatchers(HttpMethod.DELETE, "/products/**")
                .hasRole("ADMIN")

                // Demais endpoints exigem autenticação
                .anyRequest()
                .authenticated()
        );

        return http.build();
    }
}