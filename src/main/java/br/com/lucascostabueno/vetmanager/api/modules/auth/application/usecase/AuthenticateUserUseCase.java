package br.com.lucascostabueno.vetmanager.api.modules.auth.application.usecase;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.RefreshTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.AccessTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.auth.infrastructure.security.AuthenticatedUser;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthenticateUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final TokenSettings tokenSettings;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public LoginResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticateCredentials(request);
        User user = extractAuthenticatedUser(authentication);
        String accessToken = accessTokenService.generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return toLoginResponse(accessToken, refreshToken);
    }

    private Authentication authenticateCredentials(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.username(),
                        request.password()
                );

        return authenticationManager.authenticate(authToken);
    }

    private User extractAuthenticatedUser(Authentication authentication) {
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

        return authenticatedUser.getUser();
    }

    private LoginResponse toLoginResponse(String accessToken, RefreshToken refreshToken) {
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .expiresIn(getAccessTokenExpiration())
                .build();
    }

    private Long getAccessTokenExpiration() {
        return tokenSettings.getAccessTokenTimeToLive().toSeconds();
    }
}