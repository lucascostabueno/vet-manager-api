package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder encoder;

    public String generateToken(User user) {
        var now = Instant.now();
        var expiresIn = 3600L; // 1 hora

        var claims = JwtClaimsSet.builder()
                .issuer("vet-manager-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.getId().toString())
                .claim("username", user.getUsername())
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
