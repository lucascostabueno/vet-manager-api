package br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyAddressRequest(@NotBlank(message="O CEP é obrigatório")String zipCode,

@NotBlank(message="O logradouro é obrigatório")@Size(max=100,message="O logradouro deve ter no máximo 100 caracteres")String address,

@NotBlank(message="O número é obrigatório")@Size(max=10,message="O número deve ter no máximo 10 caracteres")String number,

String complement,

@NotBlank(message="O bairro é obrigatório")@Size(max=100,message="O bairro deve ter no máximo 100 caracteres")String neighborhood,

@NotBlank(message="A cidade é obrigatória")@Size(max=100,message="A cidade deve ter no máximo 100 caracteres")String city,

@NotBlank(message="O estado é obrigatório")@Size(min=2,max=2,message="O estado deve conter exatamente 2 caracteres")String state){}
