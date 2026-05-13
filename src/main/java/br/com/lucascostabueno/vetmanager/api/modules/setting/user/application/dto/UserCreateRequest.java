package br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank
        String username
) {
}
