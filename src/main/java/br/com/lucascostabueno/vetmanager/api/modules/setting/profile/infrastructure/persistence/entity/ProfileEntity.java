package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProfileEntity {
    private final UUID id;
    private final String name;
}
