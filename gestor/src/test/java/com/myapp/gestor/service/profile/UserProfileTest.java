package com.myapp.gestor.service.profile;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myapp.gestor.exception.UserNotFoundException;
import com.myapp.gestor.model.User;
import com.myapp.gestor.model.UserProfile;
import com.myapp.gestor.repository.UserProfileRepository;
import com.myapp.gestor.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserProfileTest {
    @Mock
    private UserProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserProfileService service;

    @Test
    void updateStreakByUser_ShouldUpdateStreak_WhenUserExists() {
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
                .email("test@example.com")
                .build();
        UserProfile profile = UserProfile.builder()
                .user(user)
                .streaks(0)
                .currency("USD")
                .build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        service.updateStreakByUser(userId);
        verify(profileRepository).findByUserId(userId);
    }

    @Test
    void updateStreakByUser_ShouldThrowException_WhenUserDoesNotExist() {
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.updateStreakByUser(userId));
    }

    @Test
    void updateStreakByUser_ShouldThrowException_WhenProfileDoesNotExist() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(profileRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateStreakByUser(userId));
    }
}
