package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto;

import java.util.UUID;

public record EmployeeResponse(
        UUID id,
        String name,
        String cpf,
        String email
) {
}
