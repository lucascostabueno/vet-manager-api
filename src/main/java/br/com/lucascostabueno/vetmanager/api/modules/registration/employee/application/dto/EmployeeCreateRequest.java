package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        @Email
        String email
) {
}
