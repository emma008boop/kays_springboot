package com.myapp.gestor.service.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myapp.gestor.dto.auth.LoginUserRequest;
import com.myapp.gestor.dto.auth.LoginUserResponse;
import com.myapp.gestor.exception.EmailNotFoundException;
import com.myapp.gestor.model.User;
import com.myapp.gestor.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthUserService service;

    @Test
    void login_ShouldReturnSuccessResponse_WhenCredentialsAreValid() {
        LoginUserRequest request = new LoginUserRequest("test@gmail.com", "123");
        User mockUser = new User();
        mockUser.setEmail("test@gmail.com");
        mockUser.setPasswordHash("password123");

        when(repository.findByEmail("test@gmail.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("123", "password123")).thenReturn(true);

        LoginUserResponse response = service.login(request);

        assertNotNull(response);
        assertEquals("The login has been successfully done", response.message());
        verify(repository).findByEmail("test@gmail.com");
    }

    @Test
    void login_ShouldThrowException_WhenEmailDoesNotExist() {
        LoginUserRequest request = new LoginUserRequest("ghostemail@gmail.com", "123");

        when(repository.findByEmail("ghostemail@gmail.com")).thenReturn(Optional.empty());

        assertThrows(EmailNotFoundException.class, () -> service.login(request));

    }
}
