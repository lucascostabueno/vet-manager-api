package br.com.lucascostabueno.vetmanager.api.modules.registration.employee.infrastructure.persistence.specification;

import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.application.dto.EmployeeSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.model.Employee;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecs {
    public static Specification<Employee> byFilter(EmployeeSearchFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.id() != null) {
                predicates.add(cb.equal(root.get("id"), filter.id()));
            }

            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            if (filter.email() != null && !filter.email().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filter.email().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
