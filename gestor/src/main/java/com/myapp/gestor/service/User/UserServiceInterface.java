package com.myapp.gestor.service.User;

import com.myapp.gestor.dto.User.UserDeleteAccountRequest;
import com.myapp.gestor.dto.User.UserDeleteAccountResponse;
import com.myapp.gestor.dto.User.UserUpdateAccountRequest;
import com.myapp.gestor.dto.User.UserUpdateAccountResponse;

public interface UserServiceInterface {
    UserDeleteAccountResponse deleteAccount(UserDeleteAccountRequest request);
    UserUpdateAccountResponse updateAccount(Long id, UserUpdateAccountRequest request);

}
