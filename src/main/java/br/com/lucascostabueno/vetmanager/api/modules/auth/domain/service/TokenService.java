package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;

public interface TokenService {
    String generateToken(User user);
}
