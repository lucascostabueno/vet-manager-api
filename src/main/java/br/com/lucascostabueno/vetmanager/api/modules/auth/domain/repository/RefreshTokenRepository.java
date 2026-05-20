package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository;

import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}
