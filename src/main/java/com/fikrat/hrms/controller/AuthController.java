package com.fikrat.hrms.controller;

import com.fikrat.hrms.dto.auth.JwtResponse;
import com.fikrat.hrms.dto.auth.LoginRequest;
import com.fikrat.hrms.dto.auth.MessageResponse;
import com.fikrat.hrms.dto.auth.RegisterRequest;
import com.fikrat.hrms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerAsUser(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        MessageResponse messageResponse = new MessageResponse("User registered successfully");
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
