package com.example.frankenstein.controller;

import com.example.frankenstein.dto.LoginRequest;
import com.example.frankenstein.dto.LoginResponse;
import com.example.frankenstein.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AuthController(JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        boolean usuarioCorreto = request.username().equals(adminUsername);
        boolean senhaCorreta = request.password().equals(adminPassword);

        if (!usuarioCorreto || !senhaCorreta) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.UNAUTHORIZED,
                    "Usuário ou senha inválidos"
            );
        }

        String token = jwtService.gerarToken(
                request.username(),
                List.of("ROLE_ADMIN")
        );

        return new LoginResponse(token);
    }
}