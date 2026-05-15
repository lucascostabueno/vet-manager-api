package br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {}