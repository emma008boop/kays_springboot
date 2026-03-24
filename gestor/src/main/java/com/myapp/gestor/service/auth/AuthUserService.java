package com.myapp.gestor.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.gestor.dto.auth.LoginUserRequest;
import com.myapp.gestor.dto.auth.LoginUserResponse;
import com.myapp.gestor.dto.auth.RegisterUserRequest;
import com.myapp.gestor.dto.auth.RegisterUserResponse;
import com.myapp.gestor.exception.EmailNotFoundException;
import com.myapp.gestor.model.User;
import com.myapp.gestor.repository.UserRepository;
import com.myapp.gestor.service.UserValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserService implements AuthUserServiceInterface {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService validationService;

    public RegisterUserResponse register(RegisterUserRequest dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new EmailNotFoundException("You can't use the email:" + dto.email() + "cause already exists");
        }
        User user = new User();
        user.setEmail(dto.email());
        String passwordHashed = passwordEncoder.encode(dto.passwordHash());
        user.setPasswordHash(passwordHashed);

        return new RegisterUserResponse(dto.email());
    }

    public LoginUserResponse login(LoginUserRequest dto) {
        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new EmailNotFoundException("The following email:" + dto.email() + "was not found"));
        validationService.validatePassword(dto.password(), user.getPasswordHash());

        return new LoginUserResponse("The login has been successfully done");
    }
}
