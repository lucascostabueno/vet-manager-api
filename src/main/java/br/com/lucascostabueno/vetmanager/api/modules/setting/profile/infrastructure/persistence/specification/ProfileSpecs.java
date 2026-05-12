package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.infrastructure.persistence.specification;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Profile;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProfileSpecs {
    public static Specification<Profile> byFilter(ProfileSearchFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.id() != null) {
                predicates.add(cb.equal(root.get("id"), filter.id()));
            }

            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
