package br.com.lucascostabueno.vetmanager.api.modules.auth.infrastructure.controller;

import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginRequest;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.LoginResponse;
import br.com.lucascostabueno.vetmanager.api.modules.auth.application.dto.usecase.AuthenticateUserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoint for user authentication and token generation")
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authenticateUserUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
