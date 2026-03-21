package com.myapp.gestor.service.User;

import com.myapp.gestor.dto.User.UserDeleteAccountRequest;
import com.myapp.gestor.dto.User.UserDeleteAccountResponse;
import com.myapp.gestor.dto.User.UserUpdateAccountRequest;
import com.myapp.gestor.dto.User.UserUpdateAccountResponse;
import com.myapp.gestor.exception.EmailNotFoundException;
import com.myapp.gestor.exception.InvalidCredentialsException;
import com.myapp.gestor.model.User;
import com.myapp.gestor.repository.UserRepository;
import com.myapp.gestor.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserValidationService validationService;

    public UserDeleteAccountResponse deleteAccount(UserDeleteAccountRequest request){
        User user = repository.findByEmail(request.email())
                .orElseThrow(() -> new EmailNotFoundException("User isn't registered"));

        validationService.validatePassword(request.password(), user.getPasswordHash());

        repository.delete(user);
        return new UserDeleteAccountResponse("User deleted successfully");
    }

    public UserUpdateAccountResponse updateAccount(Long id, UserUpdateAccountRequest request) {
        User user = repository.findByEmail(request.email())
                .orElseThrow(() -> new EmailNotFoundException("User isn't registered"));

        validationService.validatePassword(request.password(), user.getPasswordHash());

        user.setEmail(request.email());
        user.setPasswordHash(request.password());

        repository.save(user);
        return new UserUpdateAccountResponse(user.getEmail(), "was successfully updated");
    }
}
