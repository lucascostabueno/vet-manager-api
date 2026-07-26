package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.entity;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CompanyAddressEntity {
  private UUID id;
  private String zipCode;
  private String address;
  private String number;
  private String complement;
  private String neighborhood;
  private String city;
  private String state;
  private Company company;
}
