package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, UUID>,
    JpaSpecificationExecutor<CompanyAddress>, CompanyRepositoryCustom {
  Optional<CompanyAddress> findByCompanyId(UUID companyId);
}
