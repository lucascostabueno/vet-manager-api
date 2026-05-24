package br.com.lucascostabueno.vetmanager.api.modules.auth.application.usecase;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.factory.LoginResponseFactory;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model.RefreshToken;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.AccessTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.service.RefreshTokenService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthenticateUserUseCase {

    private final AccessTokenService accessTokenService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final LoginResponseFactory loginResponseFactory;

    @Transactional
    public LoginResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        String accessToken = accessTokenService.generateAccessToken(user);

        return loginResponseFactory.toLoginResponse(accessToken, refreshToken);
    }
}