package br.com.lucascostabueno.vetmanager.api.modules.auth.infrastructure.scheduler;

import br.com.lucascostabueno.vetmanager.api.modules.auth.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class RefreshTokenPurgeScheduler {

    private final RefreshTokenRepository refreshTokenRepository;

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional
    public void purgeGarbageTokens() {
        refreshTokenRepository.deleteByExpiresAtBeforeOrRevokedTrue(Instant.now());
    }
}
