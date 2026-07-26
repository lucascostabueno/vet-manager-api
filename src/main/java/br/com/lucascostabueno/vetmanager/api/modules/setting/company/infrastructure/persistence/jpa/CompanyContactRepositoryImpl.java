package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository.CompanyContactRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyContactRepositoryImpl implements CompanyContactRepositoryCustom {

  @PersistenceContext
  private final EntityManager entityManager;

}
