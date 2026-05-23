package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LogoutRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.RefreshTokenRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository.RefreshTokenRepository;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.RefreshTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.TokenService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenSettings tokenSettings;
    private final TokenService tokenService;

    @Override
    public RefreshToken createRefreshToken(User user) {
        Instant expiresAt = Instant.now().plus(tokenSettings.getRefreshTokenTimeToLive());
        RefreshToken refreshToken = new RefreshToken(user, expiresAt);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public LoginResponse refresh(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token."));

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
            revokeAllUserTokens(refreshToken.getUser());
            throw new BadCredentialsException("Refresh token revoked.");
        }

        if (refreshToken.isExpired()) {
            throw new CredentialsExpiredException("Refresh token expired.");
        }
    }

    @Override
    @Transactional
    public void logout(LogoutRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token."));

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!refreshToken.getUser().getId().equals(UUID.fromString(authentication.getName()))) {
            throw new AccessDeniedException("You do not have permission to revoke this token.");
        }

        refreshToken.revoke();
    }

    @Override
    @Transactional
    public void revokeAllUserTokens(User user) {
        List<RefreshToken> activeTokens = refreshTokenRepository.findAllByUserAndRevokedFalse(user);
        activeTokens.forEach(RefreshToken::revoke);
    }
}
