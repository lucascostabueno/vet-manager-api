package br.com.lucascostabueno.vetmanager.api.config.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtConfig {

  @Value("${vet.security.jwt.public-key}")
  private RSAPublicKey publicKey;

  @Value("${vet.security.jwt.private-key}")
  private RSAPrivateKey privateKey;

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey)
        .keyID("vet-manager-key").build();
    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public TokenSettings tokenSettings() {
    return TokenSettings.builder().accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
        .accessTokenTimeToLive(Duration.ofMinutes(15)).refreshTokenTimeToLive(Duration.ofDays(7))
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    String encodingId = "argon2";
    Argon2PasswordEncoder argon2 = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(encodingId, argon2);

    DelegatingPasswordEncoder delegatingPasswordEncoder =
        new DelegatingPasswordEncoder(encodingId, encoders);

    delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(argon2);

    return delegatingPasswordEncoder;
  }
}
