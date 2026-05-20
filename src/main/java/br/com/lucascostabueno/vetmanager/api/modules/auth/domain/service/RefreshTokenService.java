package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository.RefreshTokenRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenSettings tokenSettings;

    public RefreshToken createRefreshToken(User user) {

        Instant now = Instant.now();
        Instant expiresAt = now.plus(tokenSettings.getRefreshTokenTimeToLive());

        RefreshToken refreshToken = new RefreshToken(user, UUID.randomUUID().toString(), expiresAt);

        return refreshTokenRepository.save(refreshToken);
    }
}
