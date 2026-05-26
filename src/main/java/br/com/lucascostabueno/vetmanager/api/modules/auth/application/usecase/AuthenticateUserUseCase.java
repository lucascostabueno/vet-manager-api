package br.com.lucascostabueno.vetmanager.api.modules.auth.application.usecase;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.factory.LoginResponseFactory;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.AccessTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.RefreshTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.auth.infrastructure.security.AuthenticatedUser;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthenticateUserUseCase {
  private final AccessTokenService accessTokenService;
  private final RefreshTokenService refreshTokenService;
  private final LoginResponseFactory loginResponseFactory;
  private final AuthenticationManager authenticationManager;

  @Transactional
  public LoginResponse authenticate(LoginRequest request) {
    Authentication authentication = authenticateCredentials(request);
    User user = extractAuthenticatedUser(authentication);
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
    String accessToken = accessTokenService.generateAccessToken(user);

    return loginResponseFactory.toLoginResponse(accessToken, refreshToken);
  }

  private Authentication authenticateCredentials(LoginRequest request) {
    UsernamePasswordAuthenticationToken authToken =
        UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password());

    return authenticationManager.authenticate(authToken);
  }

  private User extractAuthenticatedUser(Authentication authentication) {
    AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

    return authenticatedUser.getUser();
  }
}
