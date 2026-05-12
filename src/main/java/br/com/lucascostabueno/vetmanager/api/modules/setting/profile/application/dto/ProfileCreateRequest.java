package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ProfileCreateRequest(
        @NotBlank
        String name
) {
}
