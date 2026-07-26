package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "company_contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContact {

  @Id
  @Column(name = "company_id")
  private UUID id;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "phone", nullable = false, length = 50)
  private String phone;

  @Column(name = "instagram", length = 50)
  private String instagram;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "company_id")
  private Company company;
}
