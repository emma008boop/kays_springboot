package com.myapp.gestor.service.profile;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.gestor.dto.profile.CurrencyRequestDTO;
import com.myapp.gestor.dto.profile.CurrencyResponseDTO;
import com.myapp.gestor.dto.profile.UserProfileListItemsRequest;
import com.myapp.gestor.dto.profile.UserProfileListItemsResponse;
import com.myapp.gestor.exception.UserNotFoundException;
import com.myapp.gestor.model.User;
import com.myapp.gestor.model.UserProfile;
import com.myapp.gestor.repository.UserProfileRepository;
import com.myapp.gestor.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService implements UserProfileServiceInterface {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void updateStreakByUser(Long id) {
        UserProfile profile = findEntityByUserId(id);

        profile.updateStreak();
    }

    @Override
    @Transactional
    public CurrencyResponseDTO setCurrency(CurrencyRequestDTO dto) {
        UserProfile profile = findEntityByUserId(dto.id());

        profile.setCurrency(dto.currency());
        profileRepository.save(profile);

        return new CurrencyResponseDTO(dto.currency());
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileListItemsResponse listItems(UserProfileListItemsRequest dto) {
        findEntityByUserId(dto.id());

        Page<UserProfile> itemsPage = userProfileRepository.findAllItems(dto.pageable());

        return new UserProfileListItemsResponse(itemsPage);
    }

    private UserProfile findEntityByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User wasn't found"));

        return profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("The user doesn't have a profile"));
    }
}
