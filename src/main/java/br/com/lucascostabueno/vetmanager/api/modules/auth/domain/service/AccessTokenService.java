package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.AuthUser;

public interface AccessTokenService {
  String generateAccessToken(AuthUser user);
}
