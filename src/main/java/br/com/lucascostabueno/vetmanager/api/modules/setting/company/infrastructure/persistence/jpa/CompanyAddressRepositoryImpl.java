package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository.CompanyAddressRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyAddressRepositoryImpl implements CompanyAddressRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

}
