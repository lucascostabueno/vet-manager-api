package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Permission;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PermissionService {
  List<Permission> findAllByIds(Set<UUID> ids);

  List<String> findNamesByProfileId(UUID profileId);

  void evictPermissionsCache(UUID profileId);
}
