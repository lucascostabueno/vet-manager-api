package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model;

import br.com.lucascostabueno.vetmanager.api.common.infrastructure.persistence.jpa.domain.BaseFullAuditEntity;
import br.com.lucascostabueno.vetmanager.api.modules.registration.employee.domain.model.Employee;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends BaseFullAuditEntity {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id", nullable = false)
  private Profile profile;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false, unique = true)
  private Employee employee;
}
