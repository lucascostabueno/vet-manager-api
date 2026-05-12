package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto;

import java.util.UUID;

public record ProfileSearchFilter(
        UUID id,
        String name
) {
}
