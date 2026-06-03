package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository.PermissionRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

}
