package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfileUpdateRequest(@NotBlank String name){}
