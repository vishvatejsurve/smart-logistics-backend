package com.logistics_system.service.impl;

import com.logistics_system.dto.Auth.AuthRequestDTO;
import com.logistics_system.dto.Auth.RegisterRequestDTO;
import com.logistics_system.entity.User;
import com.logistics_system.repository.UserRepository;
import com.logistics_system.security.JwtUtil;
import com.logistics_system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;
    @Override
    public void register(RegisterRequestDTO requestDTO) {
       User user = new User();
       user.setUsername(requestDTO.getUsername());
       user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
       user.setRole(requestDTO.getRole());

       repository.save(user);
    }

    @Override
    public String login(AuthRequestDTO request) {
       User user = repository.findByUsername(request.getUsername()).orElseThrow(()->new UsernameNotFoundException("User not found"));

       if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
           throw new RuntimeException("Invalid password");
       }

       return jwtUtil.generateToken(user.getUsername());
    }
}
