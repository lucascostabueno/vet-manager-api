package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.entity;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CompanyContactEntity {
  private UUID id;
  private String email;
  private String phone;
  private String instagram;
  private Company company;
}
