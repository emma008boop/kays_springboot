package com.myapp.gestor.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.gestor.dto.User.UserDeleteAccountRequest;
import com.myapp.gestor.dto.User.UserDeleteAccountResponse;
import com.myapp.gestor.dto.User.UserUpdateAccountRequest;
import com.myapp.gestor.dto.User.UserUpdateAccountResponse;
import com.myapp.gestor.service.User.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/account")
public class UserController {
    private final UserService service;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDeleteAccountResponse> deleteUser(@PathVariable Long id,
            @RequestBody @Valid UserDeleteAccountRequest request) {

        UserDeleteAccountResponse response = service.deleteAccount(request);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-info/{id}")
    public ResponseEntity<UserUpdateAccountResponse> updateUser(@PathVariable Long id,
            @RequestBody @Valid UserUpdateAccountRequest request) {

        UserUpdateAccountResponse response = service.updateAccount(id, request);

        return ResponseEntity.accepted().body(response);
    }
}
