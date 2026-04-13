package com.myapp.gestor.dto.profile;

import com.myapp.gestor.model.UserProfile;

public record CurrencyRequestDTO(Long id, String currency) {
    public CurrencyRequestDTO(UserProfile profile) {
        this(
                profile.getId(),
                profile.getCurrency());
    }
}
