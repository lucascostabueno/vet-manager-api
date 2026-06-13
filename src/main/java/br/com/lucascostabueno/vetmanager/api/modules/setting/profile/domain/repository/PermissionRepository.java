package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>,
    JpaSpecificationExecutor<Permission>, ProfileRepositoryCustom {

  @Query("SELECT p FROM Permission p WHERE p.id IN :ids")
  List<Permission> findAllByIds(@Param("ids") Set<UUID> ids);

  @Query("SELECT p.name FROM Profile prof JOIN prof.permissions p WHERE prof.id = :profileId")
  List<String> findNamesByProfileId(@Param("profileId") UUID profileId);
}
