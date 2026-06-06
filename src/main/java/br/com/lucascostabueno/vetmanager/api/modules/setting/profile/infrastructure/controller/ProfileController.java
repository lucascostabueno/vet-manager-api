package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.impl.ProfileServiceImpl;
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
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Endpoints for managing user access profiles and permissions")
public class ProfileController {

  private final ProfileServiceImpl service;

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('PROFILE_VIEW')")
  public ResponseEntity<ProfileResponse> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping
  @PreAuthorize("hasAuthority('PROFILE_CREATE')")
  public ResponseEntity<ProfileResponse> create(@RequestBody @Valid ProfileCreateRequest request) {
    ProfileResponse response = service.create(request);

    URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
        .buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('PROFILE_UPDATE')")
  public ResponseEntity<ProfileResponse> update(@PathVariable UUID id,
      @RequestBody @Valid ProfileUpdateRequest request) {
    return ResponseEntity.ok(service.update(id, request));
  }

  @GetMapping
  @PreAuthorize("hasAuthority('PROFILE_VIEW')")
  public ResponseEntity<Page<ProfileResponse>> search(@ParameterObject ProfileSearchFilter filter,
      @ParameterObject Pageable pageable) {
    return ResponseEntity.ok(service.search(filter, pageable));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('PROFILE_DELETE')")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
