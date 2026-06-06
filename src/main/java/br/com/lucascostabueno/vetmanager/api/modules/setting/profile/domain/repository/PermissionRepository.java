package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>,
    JpaSpecificationExecutor<Permission>, ProfileRepositoryCustom {
}
