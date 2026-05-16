package br.com.lucascostabueno.vetmanager.api.modules.setting.user.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.service.impl.UserServiceImpl;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreateRequest request) {
        var response = service.create(request);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UserUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> search(
            @ParameterObject UserSearchFilter filter,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.search(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}