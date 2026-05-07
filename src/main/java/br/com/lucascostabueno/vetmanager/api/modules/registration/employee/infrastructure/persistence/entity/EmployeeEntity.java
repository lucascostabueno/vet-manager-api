package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class EmployeeEntity {
    private final UUID id;
    private final String name;
    private final String cpf;
    private final String email;
}
