package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.mapper;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeResponse;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeCreateRequest request);

    EmployeeResponse toResponse(Employee entity);

    void updateEntityFromDto(EmployeeUpdateRequest dto, @MappingTarget Employee entity);
}
