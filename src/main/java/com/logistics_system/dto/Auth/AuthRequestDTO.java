package com.logistics_system.dto.Auth;

import com.logistics_system.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDTO{
    private String username;
    private String password;
    private Role role;
}
