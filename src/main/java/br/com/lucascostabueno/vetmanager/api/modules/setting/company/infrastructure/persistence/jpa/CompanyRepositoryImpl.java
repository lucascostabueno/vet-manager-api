package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository.CompanyRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

}
