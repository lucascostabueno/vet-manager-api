package br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto;

import java.util.UUID;

public record CompanySearchFilter(UUID id,String company,String cnpj,Boolean isActive){}
