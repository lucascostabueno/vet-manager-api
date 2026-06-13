package br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserUpdateRequest(@NotBlank String username,@NotBlank String password,@NotNull UUID profile){}
