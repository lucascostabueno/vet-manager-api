package br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto;

import java.util.UUID;

public record CompanyResponse(UUID id,boolean isActive,String company,String corporateName,String cnpj,String observation,CompanyContactResponse contact,CompanyAddressResponse address){}
