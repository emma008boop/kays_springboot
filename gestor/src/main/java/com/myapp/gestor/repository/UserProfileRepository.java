package com.myapp.gestor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.gestor.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(Long id);
}
