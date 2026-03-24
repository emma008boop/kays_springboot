package com.myapp.gestor.dto.profile;

import java.time.LocalDate;

import com.myapp.gestor.model.UserProfile;

public record UserProfileDTO(Integer streaks, String currency, LocalDate lastActivityDate) {
    public UserProfileDTO(UserProfile profile) {
        this(
                profile.getStreaks(),
                profile.getCurrency(),
                profile.getLastActivityDate());
    }
}
