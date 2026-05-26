package br.com.lucascostabueno.vetmanager.api.modules.auth.domain.model;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "refresh_tokens")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "token", nullable = false, unique = true)
  private String token;

  @Column(name = "expires_at", nullable = false)
  private Instant expiresAt;

  @Column(name = "revoked", nullable = false)
  private Boolean revoked = Boolean.FALSE;

  public RefreshToken(User user, Instant expiresAt) {
    this.user = user;
    this.token = UUID.randomUUID().toString();
    this.expiresAt = expiresAt;
  }

  public void revoke() {
    this.revoked = Boolean.TRUE;
  }

  public boolean isExpired() {
    return expiresAt.isBefore(Instant.now());
  }
}
