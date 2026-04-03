package com.myapp.gestor.dto.profile;

import org.springframework.data.domain.Page;

import com.myapp.gestor.model.UserProfile;

public record UserProfileListItemsResponse(Page<UserProfile> pages) {
}