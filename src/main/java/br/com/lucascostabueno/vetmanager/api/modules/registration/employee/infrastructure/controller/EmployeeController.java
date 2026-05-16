package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeResponse;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.service.impl.EmployeeServiceImpl;
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
@RequestMapping("/api/v1/employees")
@Tag(name = "Employees", description = "Endpoints for managing employees")
public class EmployeeController {

    private final EmployeeServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeCreateRequest request) {
        var response = service.create(request);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid EmployeeUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> search(
            @ParameterObject EmployeeSearchFilter filter,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.search(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}