package br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.mapper;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.*;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.CompanyAddress;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.CompanyContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", constant = "true")
  Company toEntity(CompanyCreateRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "company", ignore = true)
  CompanyContact toContactEntity(CompanyContactRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "company", ignore = true)
  CompanyAddress toAddressEntity(CompanyAddressRequest request);

  @Mapping(target = "id", ignore = true)
  void updateEntity(CompanyUpdateRequest dto, @MappingTarget Company entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "company", ignore = true)
  void updateContactEntity(CompanyContactRequest dto, @MappingTarget CompanyContact entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "company", ignore = true)
  void updateAddressEntity(CompanyAddressRequest dto, @MappingTarget CompanyAddress entity);

  @Mapping(target = "id", source = "company.id")
  @Mapping(target = "company", source = "company.company")

  @Mapping(target = "contact", source = "contact")
  @Mapping(target = "address", source = "address")
  CompanyResponse toResponse(Company company, CompanyContact contact, CompanyAddress address);

  CompanyContactResponse toContactResponse(CompanyContact entity);

  CompanyAddressResponse toAddressResponse(CompanyAddress entity);
}
