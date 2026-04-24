package com.logistics_system.service;

import com.logistics_system.dto.Auth.AuthRequestDTO;
import com.logistics_system.dto.Auth.RegisterRequestDTO;

public interface AuthService {

    void register(RegisterRequestDTO requestDTO);

    String login(AuthRequestDTO request);
}
