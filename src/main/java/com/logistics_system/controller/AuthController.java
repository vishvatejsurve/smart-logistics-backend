package com.logistics_system.controller;

import com.logistics_system.dto.Auth.AuthRequestDTO;
import com.logistics_system.dto.Auth.AuthResponseDTO;
import com.logistics_system.dto.Auth.RegisterRequestDTO;
import com.logistics_system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        String token = authService.login(request);
        return new AuthResponseDTO(token);
    }

}
