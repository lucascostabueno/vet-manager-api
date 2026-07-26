package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model;

import br.com.lucascostabueno.vetmanager.api.common.infrastructure.persistence.jpa.domain.BaseAuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Company extends BaseAuditEntity {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  @Column(name = "company", nullable = false, length = 100)
  private String company;

  @Column(name = "corporate_name", nullable = false, length = 100)
  private String corporateName;

  @Column(name = "cnpj", nullable = false, length = 14)
  private String cnpj;
}
