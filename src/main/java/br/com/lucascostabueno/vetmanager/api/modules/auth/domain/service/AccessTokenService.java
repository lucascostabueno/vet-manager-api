package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;

public interface AccessTokenService {
    String generateAccessToken(User user);
}
