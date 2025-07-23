package com.example.medibridge.dto.UserDTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
