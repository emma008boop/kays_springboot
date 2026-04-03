package com.myapp.gestor.controller.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.gestor.dto.profile.CurrencyRequestDTO;
import com.myapp.gestor.dto.profile.CurrencyResponseDTO;
import com.myapp.gestor.dto.profile.UserProfileListItemsRequest;
import com.myapp.gestor.dto.profile.UserProfileListItemsResponse;
import com.myapp.gestor.service.profile.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profile/{id}")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService profileService;

    @PatchMapping
    public ResponseEntity<Void> setStreak(@PathVariable Long id) {
        profileService.updateStreakByUser(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/currency")
    public ResponseEntity<CurrencyResponseDTO> setCurrency(@PathVariable Long id, @RequestBody CurrencyRequestDTO dto) {
        CurrencyResponseDTO response = profileService.setCurrency(dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-items")
    public ResponseEntity<UserProfileListItemsResponse> listItems(@PathVariable Long id,
            @RequestParam UserProfileListItemsRequest dto) {
        UserProfileListItemsResponse response = profileService.listItems(dto);

        return ResponseEntity.ok(response);
    }

}
