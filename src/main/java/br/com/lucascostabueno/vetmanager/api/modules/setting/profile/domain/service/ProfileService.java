package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProfileService {
    ProfileResponse findById(UUID id);

    ProfileResponse create(ProfileCreateRequest request);

    ProfileResponse update(UUID id, ProfileUpdateRequest request);

    Page<ProfileResponse> search(ProfileSearchFilter filter, Pageable pageable);

    void delete(UUID id);
}
