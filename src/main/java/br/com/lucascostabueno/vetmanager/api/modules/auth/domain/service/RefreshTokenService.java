package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LogoutRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.RefreshTokenRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    LoginResponse refresh(RefreshTokenRequest request);

    void logout(LogoutRequest request);

    void revokeAllUserTokens(User user);
}
