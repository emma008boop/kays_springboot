package com.myapp.gestor.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.gestor.dto.auth.LoginUserRequest;
import com.myapp.gestor.dto.auth.LoginUserResponse;
import com.myapp.gestor.dto.auth.RegisterUserRequest;
import com.myapp.gestor.dto.auth.RegisterUserResponse;
import com.myapp.gestor.service.auth.AuthUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthUserService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        LoginUserResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
