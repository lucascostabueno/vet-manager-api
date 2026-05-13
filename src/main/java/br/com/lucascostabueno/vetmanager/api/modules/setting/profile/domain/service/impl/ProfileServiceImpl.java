package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.mapper.ProfileMapper;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository.ProfileRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.ProfileService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.infrastructure.persistence.specification.ProfileSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Profile not found."));
    }

    @Transactional
    public ProfileResponse create(ProfileCreateRequest request) {
        var employee = mapper.toEntity(request);
        return mapper.toResponse(repository.save(employee));
    }

    @Transactional
    public ProfileResponse update(UUID id, ProfileUpdateRequest request) {
        var employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found."));

        mapper.updateEntityFromDto(request, employee);
        return mapper.toResponse(repository.save(employee));
    }

    @Transactional(readOnly = true)
    public Page<ProfileResponse> search(ProfileSearchFilter filter, Pageable pageable) {
        return repository.findAll(ProfileSpecs.byFilter(filter), pageable)
                .map(mapper::toResponse);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Invalid ID.");
        }
        repository.deleteById(id);
    }
}
