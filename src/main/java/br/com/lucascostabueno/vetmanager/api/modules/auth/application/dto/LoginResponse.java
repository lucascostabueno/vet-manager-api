package br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        Long expiresIn,
        String refreshToken
) {
}
