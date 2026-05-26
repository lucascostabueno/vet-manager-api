package br.com.lucascostabueno.vetmanager.api.modules.auth.application.factory;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginResponseFactory {

  private final TokenSettings tokenSettings;

  public LoginResponse toLoginResponse(String accessToken, RefreshToken refreshToken) {
    return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken.getToken())
        .expiresIn(tokenSettings.getAccessTokenTimeToLive().toSeconds()).build();
  }
}
