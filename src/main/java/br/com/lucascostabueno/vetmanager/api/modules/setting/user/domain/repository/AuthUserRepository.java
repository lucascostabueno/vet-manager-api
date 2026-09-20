package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
  @Query("SELECT a FROM AuthUser a WHERE a.username = :username")
  Optional<AuthUser> findByUsername(@Param("username") String username);
}
