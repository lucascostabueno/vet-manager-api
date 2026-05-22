package br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank
        String refreshToken
) {
}
