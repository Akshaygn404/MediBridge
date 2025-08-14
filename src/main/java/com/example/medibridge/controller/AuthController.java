package com.example.medibridge.controller;

import com.example.medibridge.dto.UserDTO.JwtRequest;
import com.example.medibridge.dto.UserDTO.JwtResponse;
import com.example.medibridge.dto.UserDTO.RegisterRequestDTO;
import com.example.medibridge.security.JwtUtil;
import com.example.medibridge.service.AuthService;
import com.example.medibridge.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO request) {
        boolean isRegistered = authService.registerNewUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                request.getRole()
        );
        if (isRegistered)
            return ResponseEntity.ok("User registered successfully");
        else
            return ResponseEntity.badRequest().body("User already exists");
    }



    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Incorrect email or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        // Get user role
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "").toLowerCase();

        // Return both token and role
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer")
    public String home() {
        return "Welcome to MediBridge Auth!";
    }
}