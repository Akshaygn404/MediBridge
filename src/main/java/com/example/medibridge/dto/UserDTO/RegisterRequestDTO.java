package com.example.medibridge.dto.UserDTO;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
}