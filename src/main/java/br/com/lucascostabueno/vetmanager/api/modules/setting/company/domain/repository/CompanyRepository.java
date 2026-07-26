package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>,
    JpaSpecificationExecutor<Company>, CompanyRepositoryCustom {
}
