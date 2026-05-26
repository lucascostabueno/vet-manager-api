package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    @Query("SELECT r FROM RefreshToken r WHERE r.token = :token")
    Optional<RefreshToken> findByToken(@Param("token") String token);

    @Query("SELECT r FROM RefreshToken r WHERE r.user = :user AND r.revoked = false")
    List<RefreshToken> findAllByUserAndRevokedFalse(@Param("user") User user);

    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.expiresAt < :now OR r.revoked = true")
    void deleteByExpiresAtBeforeOrRevokedTrue(@Param("now") Instant now);
}
