package br.com.lucascostabueno.vetmanager.api.config.security;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter
    implements Converter<Jwt, AbstractAuthenticationToken> {

  private final PermissionService permissionService;

  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    String profileIdStr = jwt.getClaimAsString("profile_id");
    UUID profileId = UUID.fromString(profileIdStr);

    List<String> permissionNames = permissionService.findNamesByProfileId(profileId);

    List<SimpleGrantedAuthority> authorities =
        permissionNames.stream().map(SimpleGrantedAuthority::new).toList();

    return new JwtAuthenticationToken(jwt, authorities);
  }
}
