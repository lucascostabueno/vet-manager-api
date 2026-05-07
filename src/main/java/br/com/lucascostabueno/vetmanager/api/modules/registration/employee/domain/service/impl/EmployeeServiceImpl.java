package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeResponse;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.mapper.EmployeeMapper;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.repository.EmployeeRepository;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.service.EmployeeService;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.infrastructure.persistence.specification.EmployeeSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Employee not found."));
    }

    @Transactional
    public EmployeeResponse create(EmployeeCreateRequest request) {
        if (repository.existsByCpf(request.cpf())) {
            throw new RuntimeException("Conflict: CPF already registered.");
        }
        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Conflict: Email already registered.");
        }
        var employee = mapper.toEntity(request);
        return mapper.toResponse(repository.save(employee));
    }

    @Transactional
    public EmployeeResponse update(UUID id, EmployeeUpdateRequest request) {
        if (repository.existsByCpf(request.cpf())) {
            throw new RuntimeException("Conflict: CPF already registered.");
        }
        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Conflict: Email already registered.");
        }
        var employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found."));

        mapper.updateEntityFromDto(request, employee);
        return mapper.toResponse(repository.save(employee));
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> search(EmployeeSearchFilter filter, Pageable pageable) {
        return repository.findAll(EmployeeSpecs.byFilter(filter), pageable)
                .map(mapper::toResponse);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Invalid ID.");
        }
        repository.deleteById(id);
    }
}
