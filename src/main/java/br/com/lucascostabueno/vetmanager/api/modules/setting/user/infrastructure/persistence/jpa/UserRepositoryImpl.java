package br.com.lucascostabueno.vetmanager.api.modules.setting.user.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

}
