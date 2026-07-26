package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanySearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {
  CompanyResponse findById(UUID id);

  CompanyResponse create(CompanyCreateRequest request);

  CompanyResponse update(UUID id, CompanyUpdateRequest request);

  Page<CompanyResponse> search(CompanySearchFilter filter, Pageable pageable);

  void delete(UUID id);
}
