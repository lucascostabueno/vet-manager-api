package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository
    extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>, UserRepositoryCustom {
  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findByUsername(@Param("username") String username);
}
