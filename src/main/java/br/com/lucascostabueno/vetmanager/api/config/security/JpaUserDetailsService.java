package br.com.lucascostabueno.vetmanager.api.config.security;

import br.com.lucascostabueno.vetmanager.api.modules.auth.infrastructure.security.AuthenticatedUser;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
  private final AuthUserRepository authUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return authUserRepository.findByUsername(username).map(AuthenticatedUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found."));
  }
}
