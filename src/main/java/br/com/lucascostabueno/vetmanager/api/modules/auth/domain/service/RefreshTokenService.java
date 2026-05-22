package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.RefreshTokenRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository.RefreshTokenRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenSettings tokenSettings;
    private final TokenService tokenService;

    public RefreshToken createRefreshToken(User user) {

        Instant expiresAt = Instant.now().plus(tokenSettings.getRefreshTokenTimeToLive());
        RefreshToken refreshToken = new RefreshToken(user, expiresAt);

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public LoginResponse refresh(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token."));

        validateRefreshToken(refreshToken);

        refreshToken.revoke();

        RefreshToken newRefreshToken = createRefreshToken(refreshToken.getUser());
        String accessToken = tokenService.generateToken(refreshToken.getUser());
        Long expiresIn = tokenSettings.getAccessTokenTimeToLive().toSeconds();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.getToken())
                .expiresIn(expiresIn)
                .build();
    }

    private void validateRefreshToken(RefreshToken refreshToken) {

        if (Boolean.TRUE.equals(refreshToken.getRevoked())) {
            throw new RuntimeException("Refresh token revoked.");
        }

        if (refreshToken.isExpired()) {
            throw new RuntimeException("Refresh token expired.");
        }
    }
}
