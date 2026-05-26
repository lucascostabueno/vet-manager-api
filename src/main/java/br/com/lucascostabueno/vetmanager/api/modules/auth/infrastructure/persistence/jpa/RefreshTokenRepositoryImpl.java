package br.com.lucascostabueno.vetmanager.api.modules.auth.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository.RefreshTokenRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

}
