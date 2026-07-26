package br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record CompanyUpdateRequest(@NotBlank(message="O nome fantasia é obrigatório")@Size(max=100,message="O nome fantasia deve ter no máximo 100 caracteres")String company,

@NotBlank(message="A razão social é obrigatória")@Size(max=100,message="A razão social deve ter no máximo 100 caracteres")String corporateName,

@NotBlank(message="O CNPJ é obrigatório")@CNPJ(message="O CNPJ informado é inválido")String cnpj,

@Valid @NotNull(message="Os dados de contato são obrigatórios")CompanyContactRequest contact,

@Valid @NotNull(message="Os dados de endereço são obrigatórios")CompanyAddressRequest address){}
