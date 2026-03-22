package com.myapp.gestor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.gestor.exception.InvalidCredentialsException;

@Service
public class UserValidationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validatePassword(String dbPassword, String password) {
        if (!passwordEncoder.matches(password, dbPassword)) {
            throw new InvalidCredentialsException("The password you entered is incorrect");
        }
    }
}
