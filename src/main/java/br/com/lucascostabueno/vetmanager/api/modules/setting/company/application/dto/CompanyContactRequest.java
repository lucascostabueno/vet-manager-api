package br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyContactRequest(@NotBlank(message="O e-mail é obrigatório")@Email(message="O e-mail informado é inválido")@Size(max=100,message="O e-mail deve ter no máximo 100 caracteres")String email,

@NotBlank(message="O telefone é obrigatório")@Size(max=50,message="O telefone deve ter no máximo 50 caracteres")String phone,

@Size(max=50,message="O instagram deve ter no máximo 50 caracteres")String instagram){}
