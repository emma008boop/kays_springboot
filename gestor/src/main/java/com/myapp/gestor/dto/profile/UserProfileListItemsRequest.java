package com.myapp.gestor.dto.profile;

import org.springframework.data.domain.Pageable;

public record UserProfileListItemsRequest(Long id, Pageable pageable) {
}
