package com.myapp.gestor.service.profile;

import com.myapp.gestor.dto.profile.UserProfileDTO;

public interface UserProfileServiceInterface {

    UserProfileDTO updateStreakByUser(Long id);

    UserProfileDTO setCurrency(String currency, Long id);
}
