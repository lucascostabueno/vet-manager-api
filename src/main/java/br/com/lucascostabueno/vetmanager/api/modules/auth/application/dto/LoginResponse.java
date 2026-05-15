package br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
