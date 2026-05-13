package br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto;

import java.util.UUID;

public record UserSearchFilter(
        UUID id,
        String username
) {
}
