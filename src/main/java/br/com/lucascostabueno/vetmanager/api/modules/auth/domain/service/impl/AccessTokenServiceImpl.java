package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.AccessTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

  private final JwtEncoder encoder;
  private final TokenSettings tokenSettings;

  @Override
  public String generateAccessToken(AuthUser user) {
    Instant now = Instant.now();
    Instant expiresAt = now.plus(tokenSettings.getAccessTokenTimeToLive());

    JwtClaimsSet claims = JwtClaimsSet.builder().issuer("vet-manager-api").issuedAt(now)
        .expiresAt(expiresAt).subject(user.getId().toString()).claim("username", user.getUsername())
        .claim("tenant_id", user.getTenantId())
        .claim("profile_id", user.getProfile().getId().toString()).build();

    return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
