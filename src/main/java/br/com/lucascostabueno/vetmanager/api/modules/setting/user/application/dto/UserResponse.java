package br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username
) {
}
