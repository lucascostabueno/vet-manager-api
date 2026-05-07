package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.infrastructure.persistence.jpa;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.repository.EmployeeRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

}
