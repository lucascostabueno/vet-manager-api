package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Permission;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository.PermissionRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
  private final PermissionRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<Permission> findAllByIds(Set<UUID> ids) {
    return Optional.ofNullable(ids).filter(set -> !set.isEmpty()).map(repository::findAllByIds)
        .orElse(List.of());
  }

  @Override
  @Transactional(readOnly = true)
  public List<String> findNamesByProfileId(UUID profileId) {
    return Optional.ofNullable(profileId).map(repository::findNamesByProfileId).orElse(List.of());
  }
}
