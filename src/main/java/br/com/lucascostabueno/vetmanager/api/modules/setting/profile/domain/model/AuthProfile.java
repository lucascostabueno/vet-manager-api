package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Immutable
@RequiredArgsConstructor
@Table(name = "profiles")
public class AuthProfile {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "tenant_id")
  private String tenantId;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "profile_permissions", joinColumns = @JoinColumn(name = "profile_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private Set<Permission> permissions;
}
