package com.myapp.gestor.service.profile;

import com.myapp.gestor.dto.profile.CurrencyRequestDTO;
import com.myapp.gestor.dto.profile.CurrencyResponseDTO;
import com.myapp.gestor.dto.profile.UserProfileListItemsRequest;
import com.myapp.gestor.dto.profile.UserProfileListItemsResponse;

public interface UserProfileServiceInterface {

    void updateStreakByUser(Long id);

    CurrencyResponseDTO setCurrency(CurrencyRequestDTO dto);

    UserProfileListItemsResponse listItems(UserProfileListItemsRequest dto);
}
