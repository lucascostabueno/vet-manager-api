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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Endpoints for managing user access profiles and permissions")
public class ProfileController {

    private final ProfileServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(@RequestBody @Valid ProfileCreateRequest request) {
        var response = service.create(request);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid ProfileUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<ProfileResponse>> search(
            @ParameterObject ProfileSearchFilter filter,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.search(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}