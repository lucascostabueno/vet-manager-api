package br.com.lucascostabueno.vetmanager.api.common.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.common.application.dto.IdentifiableDTO;
import br.com.lucascostabueno.vetmanager.api.common.domain.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;

public abstract class BaseController<T, ID extends Serializable, RequestDTO, ResponseDTO extends IdentifiableDTO<ID>>
    extends BaseReadController<T, ID, RequestDTO, ResponseDTO> {

  protected final BaseService<T, ID, RequestDTO, ResponseDTO> service;
  private final String resourceName;

  protected BaseController(BaseService<T, ID, RequestDTO, ResponseDTO> service,
      String resourceName) {
    super(service, resourceName);
    this.service = service;
    this.resourceName = resourceName.toUpperCase();
  }

  public String getAuthorityCreate() {
    return this.resourceName + "_CREATE";
  }

  public String getAuthorityUpdate() {
    return this.resourceName + "_UPDATE";
  }

  public String getAuthorityDelete() {
    return this.resourceName + "_DELETE";
  }

  @PostMapping
  @PreAuthorize("hasAuthority(this.getAuthorityCreate())")
  public ResponseEntity<ResponseDTO> create(@RequestBody @Valid RequestDTO request) {
    ResponseDTO response = service.create(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority(this.getAuthorityUpdate())")
  public ResponseEntity<ResponseDTO> update(@PathVariable ID id,
      @RequestBody @Valid RequestDTO request) {
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority(this.getAuthorityDelete())")
  public ResponseEntity<Void> delete(@PathVariable ID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
