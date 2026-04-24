package com.logistics_system.dto.Auth;

import com.logistics_system.enums.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private Role role;
}
