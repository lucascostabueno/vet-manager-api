package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto;

import java.util.UUID;

public record ProfileResponse(
        UUID id,
        String name
) {
}
