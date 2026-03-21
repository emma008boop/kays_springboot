package com.myapp.gestor.controller.user;

import com.myapp.gestor.dto.User.UserDeleteAccountRequest;
import com.myapp.gestor.dto.User.UserDeleteAccountResponse;
import com.myapp.gestor.dto.User.UserUpdateAccountRequest;
import com.myapp.gestor.dto.User.UserUpdateAccountResponse;
import com.myapp.gestor.service.User.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/user/account")
public class UserController {
    @Autowired
    private UserService service;

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<UserDeleteAccountResponse> deleteUser(@PathVariable Long id, @RequestBody @Valid UserDeleteAccountRequest request) {

        UserDeleteAccountResponse response = service.deleteAccount(request);

        return ResponseEntity.ok(response);
    }

    @PatchMapping ("/update-info/{id}")
    public ResponseEntity<UserUpdateAccountResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateAccountRequest request) {

        UserUpdateAccountResponse response = service.updateAccount(id, request);

        return ResponseEntity.accepted().body(response);
    }
}
