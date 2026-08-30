package br.com.lucascostabueno.vetmanager.api.common.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.common.application.dto.IdentifiableDTO;
import br.com.lucascostabueno.vetmanager.api.common.domain.service.BaseReadService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.List;

public abstract class BaseReadController<T, ID extends Serializable, RequestDTO, ResponseDTO extends IdentifiableDTO<ID>> {

  protected final BaseReadService<T, ID, RequestDTO, ResponseDTO> readService;
  private final String resourceName;

  protected BaseReadController(BaseReadService<T, ID, RequestDTO, ResponseDTO> readService,
      String resourceName) {
    this.readService = readService;
    this.resourceName = resourceName.toUpperCase();
  }

  public String getAuthorityView() {
    return this.resourceName + "_VIEW";
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority(this.getAuthorityView())")
  public ResponseEntity<ResponseDTO> getById(@PathVariable ID id) {
    return ResponseEntity.ok(readService.findById(id));
  }

  @GetMapping
  @PreAuthorize("hasAuthority(this.getAuthorityView())")
  public ResponseEntity<Page<ResponseDTO>> searchPaged(@ParameterObject RequestDTO filter,
      @ParameterObject Pageable pageable) {
    return ResponseEntity.ok(readService.searchPaged(filter, pageable));
  }

  @GetMapping("/all")
  @PreAuthorize("hasAuthority(this.getAuthorityView())")
  public ResponseEntity<List<ResponseDTO>> searchAll(@ParameterObject RequestDTO filter) {
    return ResponseEntity.ok(readService.search(filter));
  }
}
