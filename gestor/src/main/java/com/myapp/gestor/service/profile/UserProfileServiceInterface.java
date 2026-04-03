package com.myapp.gestor.service.profile;

import com.myapp.gestor.dto.profile.UserProfileDTO;
import com.myapp.gestor.dto.profile.UserProfileListItemsRequest;
import com.myapp.gestor.dto.profile.UserProfileListItemsResponse;

public interface UserProfileServiceInterface {

    UserProfileDTO updateStreakByUser(Long id);

    UserProfileDTO setCurrency(String currency, Long id);

    UserProfileListItemsResponse listItems(UserProfileListItemsRequest dto);
}
