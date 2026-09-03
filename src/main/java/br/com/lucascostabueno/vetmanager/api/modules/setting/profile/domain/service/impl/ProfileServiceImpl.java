package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.config.cache.CacheConstants;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.mapper.ProfileMapper;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Permission;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Profile;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository.ProfileRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.PermissionService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.ProfileService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.infrastructure.persistence.specification.ProfileSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository repository;
  private final PermissionService permissionService;
  private final ProfileMapper mapper;

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = CacheConstants.PROFILES, key = "#id")
  public ProfileResponse findById(UUID id) {
    return repository.findById(id).map(mapper::toResponse)
        .orElseThrow(() -> new RuntimeException("Profile not found."));
  }

  @Override
  @Transactional
  public ProfileResponse create(ProfileCreateRequest request) {
    Profile profile = mapper.toEntity(request);

    Optional.ofNullable(request.permissionIds()).filter(ids -> !ids.isEmpty()).ifPresent(ids -> {
      List<Permission> permissions = permissionService.findAllByIds(ids);
      profile.setPermissions(new HashSet<>(permissions));
    });

    return mapper.toResponse(repository.save(profile));
  }

  @Override
  @Transactional
  @CachePut(value = CacheConstants.PROFILES, key = "#id")
  public ProfileResponse update(UUID id, ProfileUpdateRequest request) {
    Profile profile =
        repository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found."));

    mapper.updateEntity(request, profile);

    Optional.ofNullable(request.permissionIds()).filter(ids -> !ids.isEmpty())
        .ifPresentOrElse(ids -> {
          List<Permission> permissions = permissionService.findAllByIds(ids);
          profile.setPermissions(new HashSet<>(permissions));
        }, () -> profile.getPermissions().clear());

    ProfileResponse response = mapper.toResponse(repository.save(profile));

    permissionService.evictPermissionsCache(id);

    return response;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ProfileResponse> search(ProfileSearchFilter filter, Pageable pageable) {
    return repository.findAll(ProfileSpecs.byFilter(filter), pageable).map(mapper::toResponse);
  }

  @Override
  @Transactional
  @CacheEvict(value = CacheConstants.PROFILES, key = "#id")
  public void delete(UUID id) {
    if (!repository.existsById(id)) {
      throw new RuntimeException("Invalid ID.");
    }
    repository.deleteById(id);

    permissionService.evictPermissionsCache(id);
  }
}
