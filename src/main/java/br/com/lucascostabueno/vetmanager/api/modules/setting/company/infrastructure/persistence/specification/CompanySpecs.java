package br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.specification;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanySearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CompanySpecs {
  public static Specification<Company> byFilter(CompanySearchFilter filter) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (filter.id() != null) {
        predicates.add(cb.equal(root.get(Company_.id), filter.id()));
      }

      if (StringUtils.hasText(filter.company())) {
        predicates.add(cb.like(cb.lower(root.get(Company_.company)),
            "%" + filter.company().toLowerCase() + "%"));
      }

      if (StringUtils.hasText(filter.cnpj())) {
        predicates.add(cb.equal(root.get(Company_.cnpj), filter.cnpj()));
      }

      if (filter.isActive() != null) {
        predicates.add(cb.equal(root.get(Company_.isActive), filter.isActive()));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
