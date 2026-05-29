package br.com.lucascostabueno.vetmanager.api.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;
import java.util.UUID;


@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig implements AuditorAware<UUID> {

  @Override
  public Optional<UUID> getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof JwtAuthenticationToken jwtAuthentication)) {
      return Optional.empty();
    }

    UUID authenticatedUserId = UUID.fromString(jwtAuthentication.getToken().getSubject());

    return Optional.of(authenticatedUserId);
  }

}
