package br.com.lucascostabueno.vetmanager.api.modules.setting.user.infrastructure.persistence.specification;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecs {
    public static Specification<User> byFilter(UserSearchFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.id() != null) {
                predicates.add(cb.equal(root.get("id"), filter.id()));
            }

            if (filter.username() != null && !filter.username().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("username")), "%" + filter.username().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
