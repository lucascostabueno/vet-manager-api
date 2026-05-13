package br.com.lucascostabueno.vetmanager.api.modules.setting.user.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserEntity {
    private final UUID id;
    private final String username;
}
