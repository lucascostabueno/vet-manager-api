package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CompanyEntity {
  private UUID id;
  private boolean isActive;
  private String company;
  private String corporateName;
  private String cnpj;
  private String observation;
}
