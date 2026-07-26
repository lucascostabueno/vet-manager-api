package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.CompanyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyContactRepository extends JpaRepository<CompanyContact, UUID>,
    JpaSpecificationExecutor<CompanyContact>, CompanyRepositoryCustom {
  Optional<CompanyContact> findByCompanyId(UUID companyId);
}
