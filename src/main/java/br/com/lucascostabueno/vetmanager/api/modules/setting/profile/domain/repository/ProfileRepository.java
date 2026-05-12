package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID>, JpaSpecificationExecutor<Profile>, ProfileRepositoryCustom {
}
