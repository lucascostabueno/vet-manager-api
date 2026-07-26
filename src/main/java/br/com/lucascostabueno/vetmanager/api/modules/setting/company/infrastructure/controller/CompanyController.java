package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanySearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@Tag(name = "Companies", description = "Endpoints for managing companies")
public class CompanyController {

  private final CompanyService service;

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('COMPANY_VIEW')")
  public ResponseEntity<CompanyResponse> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping
  @PreAuthorize("hasAuthority('COMPANY_CREATE')")
  public ResponseEntity<CompanyResponse> create(@RequestBody @Valid CompanyCreateRequest request) {
    CompanyResponse response = service.create(request);

    URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
        .buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
  public ResponseEntity<CompanyResponse> update(@PathVariable UUID id,
      @RequestBody @Valid CompanyUpdateRequest request) {
    return ResponseEntity.ok(service.update(id, request));
  }

  @GetMapping
  @PreAuthorize("hasAuthority('COMPANY_VIEW')")
  public ResponseEntity<Page<CompanyResponse>> search(@ParameterObject CompanySearchFilter filter,
      @ParameterObject Pageable pageable) {
    return ResponseEntity.ok(service.search(filter, pageable));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('COMPANY_DELETE')")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
