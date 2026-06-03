package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.UUID;

public record ProfileUpdateRequest(@NotBlank String name,Set<UUID>permissionIds){}
