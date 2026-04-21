package com.myapp.gestor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.gestor.model.User;
import com.myapp.gestor.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

        private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User is not registered"));

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                user.getRoles()
                                .forEach(role -> authorityList.add(
                                                new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

                user.getRoles().stream()
                                .flatMap(role -> role.getPermissionList().stream())
                                .forEach(permission -> authorityList.add(
                                                new SimpleGrantedAuthority(permission.getName())));

                return new org.springframework.security.core.userdetails.User(
                                user.getUsername(),
                                user.getPasswordHash(),
                                user.isEnable(),
                                user.isAccountNoExpired(),
                                user.isCredentialsNoExpired(),
                                user.isAccountNoBlocked(),
                                authorityList);
        }
}
