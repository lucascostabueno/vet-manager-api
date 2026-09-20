package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.AuthProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Entity
@Getter
@Immutable
@RequiredArgsConstructor
@Table(name = "users")
public class AuthUser {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "tenant_id")
  private String tenantId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id", insertable = false, updatable = false)
  private AuthProfile profile;
}
