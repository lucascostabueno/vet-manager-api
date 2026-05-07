package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeResponse;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse findById(UUID id);

    EmployeeResponse create(EmployeeCreateRequest request);

    EmployeeResponse update(UUID id, EmployeeUpdateRequest request);

    Page<EmployeeResponse> search(EmployeeSearchFilter filter, Pageable pageable);

    void delete(UUID id);
}
